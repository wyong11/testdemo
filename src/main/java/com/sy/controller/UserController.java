package com.sy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sy.entity.User;
import com.sy.dao.CountDao;
import com.sy.dao.ExamDao;
import com.sy.dao.SignDao;
import com.sy.entity.Class;
import com.sy.entity.Exam;
import com.sy.entity.ExamClose;
import com.sy.entity.Grade;
import com.sy.entity.Member;
import com.sy.entity.Paper;
import com.sy.entity.PaperContent;
import com.sy.entity.PaperInfo;
import com.sy.entity.Sign;
import com.sy.entity.SignStatus;
import com.sy.entity.Team;
import com.sy.service.ExamCloseService;
import com.sy.service.ExamService;
import com.sy.service.GradeService;
import com.sy.service.SignService;
import com.sy.service.UserService;
import com.sy.giteaapi.GitUtil;
import com.sy.giteaapi.GiteaRestApi;
import com.sy.giteaapi.GiteaSSLFactory;
import com.sy.giteaapi.RestApi;

import net.sf.json.JsonConfig;


@Controller
//@RequestMapping("/user")

//这里用了@SessionAttributes，可以直接把model中的user(也就key)放入其中
//这样保证了session中存在user这个对象
//@SessionAttributes("user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private SignService signService;
	
	@Autowired
	private ExamService examService;
	@Autowired
	private ExamCloseService examCloseService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private SignDao signDao;
	
	@Autowired
	private CountDao countDao;

	// 正常访问login页面
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// 表单提交过来的路径
	@RequestMapping("/checkLogin")
	public String checkLogin(User user,int isAdmin,String name,Model model,HttpServletRequest request) {
		// 调用service方法
		
		user = userService.checkLogin(user.getName(),user.getEmail());
		String username = request.getParameter("name");  //从登录界面取用户参数  
		request.getSession().setAttribute("username",username);//将用户名保存在整个会话期间  
		System.out.println(user.getIsAdmin());
		// 若有user则添加到model里并且跳转到成功页面
		if (user != null && user.getIsAdmin() == 1) {
			model.addAttribute("user", user);
			return "t_home";
		}
		if(user != null && user.getIsAdmin() == 0) {
			model.addAttribute("user", user);
			return "s_home";
		} else {
			return "fail";
		}
		
	}
	
	//注册
	@RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/doRegist")
    public String doRegist(User user,Model model){
        System.out.println(user.getName());
        userService.Regist(user);
        return "login";
    }

	// 测试超链接跳转到另一个页面是否可以取到session值
	@RequestMapping("/insert")
	public String hrefpage() {

		return "addUser";
	}

	@RequestMapping("/addclass")
	public String hrefpage1() {

		return "addClass";
	}
	
	//获取班级列表
	@RequestMapping("/classlist")
	public String hrefpage1_1(Model model) {
		RestApi api = new GiteaRestApi();
		String result = api.createBuilder()
			.addAuthenticator("httpbasic","wy","123456")
			.addSSL(new GiteaSSLFactory())
			.buildClient()
			//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934838@qq.com\", \"username\":\"zqwwr\",\"password\":\"123456\"}");
		
			.execute("localhost",3000,"/api/v1/admin/orgs","get","");
			
			System.out.println(result);
			List<Class> list = JSONObject.parseArray(result,Class.class);
			model.addAttribute("list",list);
		/*	
		 * JSONObject json = JSON.parseObject(result); JSONArray
		 * arr=json.getJSONArray("resultData"); String js=JSONObject.toJSONString(arr,
		 * SerializerFeature.WriteClassName); List<Class> collection =
		 * JSONObject.parseArray(js, Class.class);
		 */
		
		 //List<Class> list2 = JSONArray.toList(result, new Class(), new JsonConfig());
			//JSONObject json = JSON.parseObject(result);
			//List<String> list1 = (List)JSON.parseObject(result);
			//model.addAttribute("list",list);
			//System.out.println(json.get("username"));
		//String username = (String) json.get("username");
		//request.setAttribute("username",username);
		//System.out.println(username);
		//return result;
		//return "addStudent";

		return "classList";
	}
	
	//获取班级列表
		@RequestMapping("/classlist_work")
		public String classlist(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934838@qq.com\", \"username\":\"zqwwr\",\"password\":\"123456\"}");
			
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			/*	
			 * JSONObject json = JSON.parseObject(result); JSONArray
			 * arr=json.getJSONArray("resultData"); String js=JSONObject.toJSONString(arr,
			 * SerializerFeature.WriteClassName); List<Class> collection =
			 * JSONObject.parseArray(js, Class.class);
			 */
			
			 //List<Class> list2 = JSONArray.toList(result, new Class(), new JsonConfig());
				//JSONObject json = JSON.parseObject(result);
				//List<String> list1 = (List)JSON.parseObject(result);
				//model.addAttribute("list",list);
				//System.out.println(json.get("username"));
			//String username = (String) json.get("username");
			//request.setAttribute("username",username);
			//System.out.println(username);
			//return result;
			//return "addStudent";

			return "classList_work";
		}

	/*
	 * @RequestMapping("/worklist") public String worklist() {
	 * 
	 * return "workList"; }
	 */
		
	@RequestMapping("/home")
	public String hrefpage2() {

		return "gohome";
	}



	/*
	 * @RequestMapping("/worklist") public String hrefpage4() {
	 * 
	 * return "workList"; }
	 */

	/*
	 * @RequestMapping("/addsign") public String hrefpage5() {
	 * 
	 * return "addSign"; }
	 */

	/*
	 * @RequestMapping("/signlist") public String hrefpage6() {
	 * 
	 * return "signList"; }
	 */
	@RequestMapping("/addexam0")
	public String addexam(String url,String localurl,HttpServletRequest request) throws IOException {
		
		File file = new File("C:/Users/wyong/Desktop/copy/copy.java");
		FileReader fileReader = new FileReader(file);  
        BufferedReader bReader = new BufferedReader(fileReader);  
          
        //String tempString = null; 
        int num = 0;
        char ch;
        while ( (num = bReader.read()) != -1 ) {  
        	ch = (char) num;
            System.out.print(ch);
           // System.out.println(tempString);  
            request.setAttribute("tempString",ch);
        }  
        
        bReader.close();  
        fileReader.close();  
   	
   	 	
        
		return "addExam";
	}
	@RequestMapping("/addexam2")
	public String addexam2(String url,String localurl,HttpServletRequest request) throws IOException {
		
		/*
		 * File file = new File("C:/Users/wyong/Desktop/copy/copy.java"); FileReader
		 * fileReader = new FileReader(file); BufferedReader bReader = new
		 * BufferedReader(fileReader);
		 */
          
        StringBuilder texts =new StringBuilder();    
        InputStreamReader isr = new InputStreamReader(new FileInputStream("C:/Users/wyong/Desktop/copy/copy.java"), "UTF-8");//加上编码转换
        BufferedReader br = new BufferedReader(isr);
        String line = null;  
        while ((line = br.readLine()) != null) {   
              texts.append(line);  
              System.out.println(line);
         	 request.setAttribute("tempString",line);
        }  
        br.close();
		return "addExam";
	}

	/*
	 * @RequestMapping("/examlist3") public String hrefpage8() {
	 * 
	 * return "examList"; }
	 */
	
	// 注销方法
	@RequestMapping("/outLogin")
	public String outLogin(HttpSession session) {
		// 通过session.invalidata()方法来注销当前的session
		session.invalidate();
		return "login";
	}

	/*
	 * @RequestMapping("/addUser") public String addUser(List<Map<String, Object>>
	 * userList){
	 * 
	 * try { userService.insertSalesChannel(userList); } catch (Exception e) { //
	 * TODO Auto-generated catch block e.printStackTrace(); }
	 * 
	 * return "success"; }
	 */

	/*
	 * @RequestMapping("registerInfo") public String
	 * register(@RequestParam("username") int username, @RequestParam("userNum") int
	 * userNum, Model model) {
	 * 
	 * userService.addUser(user);
	 * 
	 * } else { model.addAttribute("InfoMessage", "addUser failed!"); } return
	 * "login";
	 * 
	 * }
	 */

	
	 @RequestMapping("/addUsers")
	 public String adduser(HttpServletRequest request) {
		 RestApi api = new GiteaRestApi();
		 String result = api.createBuilder()
				 .addAuthenticator("httpbasic","wy","123456")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
	 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
	 
				 .execute("localhost",3000,"/api/v1/orgs/162011","get","");
	 
	 System.out.println(result);
	 JSONObject json = JSON.parseObject(result);
	 //System.out.println(json.get("username"));
	 String username = (String)json.get("username");
	 request.setAttribute("username",username);
	 System.out.println(username);
	 //return result;
	 return "addStudent";
	 }
	 
	 @ResponseBody
	 @RequestMapping("/addClass")
	 public String addClass(HttpServletRequest request,String username,String fullname,String description) {
		 RestApi api = new GiteaRestApi();
		 String result = api.createBuilder()
				 .addAuthenticator("httpbasic","wy","123456")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
	 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
	 
				 .execute("localhost",3000,"/api/v1/orgs","post","{\"description\":\""+description+"\",\"full_name\":\""+fullname+"\",\"username\":\""+username+"\"}");
					
	 System.out.println(result);
	 //JSONObject json = JSON.parseObject(result);
	 //System.out.println(json.get("username"));
	 //String username = (String)json.get("username");
	 //request.setAttribute("username",username);
	 //System.out.println(username);
	 return result;
	 //return "addStudent";
	 }
	 
	@ResponseBody
	@RequestMapping("/addUsers2") 
	public String adduser2(HttpServletRequest request) {
		RestApi api = new GiteaRestApi();
		String result = api.createBuilder()
			.addAuthenticator("httpbasic","wy","123456")
			.addSSL(new GiteaSSLFactory())
			.buildClient()
			//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934838@qq.com\", \"username\":\"zqwwr\",\"password\":\"123456\"}");
		
			.execute("localhost",3000,"/api/v1/orgs","post","{\"description\":\"add a class\",\"full_name\":\"162011班\",\"username\":\"162011\"}");
			
			System.out.println(result);
			//JSONObject json = JSON.parseObject(result);
			//System.out.println(jsonObject.get("username"));
		//String username = (String) json.get("username");
		//request.setAttribute("username",username);
		//System.out.println(username);
		return result;
		//return "addStudent";
	}
	
	//获取成员
	@RequestMapping("/getmembers")
	 public String getMembers(String username,Model model) {
		 RestApi api = new GiteaRestApi();
		 String result = api.createBuilder()
				 .addAuthenticator("httpbasic","wy","123456")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
				 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
		 String result2 = api.createBuilder()
				 .addAuthenticator("httpbasic","wy","123456")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
				 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/teams","get");
		 	System.out.println(result);
			List<Member> list = JSONObject.parseArray(result,Member.class);
			model.addAttribute("list",list);
			
			List<Team> list2 = JSONObject.parseArray(result2,Team.class);
			model.addAttribute("list2",list2);
	 //return result;
			return "showMembers";
	 }
	
	
	//在组织中添加团队
		@RequestMapping("/addteam")
		public String addteam() {

			return "addTeam";
		}
		
		//新增团队
		 @RequestMapping("/addTeam")
		 public String addTeam(HttpServletRequest request,String id,String name,String description) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
		 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
		 
					 .execute("localhost",3000,"/api/v1/orgs/"+id+"/teams","post","{\"description\":\""+description+"\",\"name\":\""+name+"\",\"permission\":\"read\",\"units\":[\"\"]}");
						
		 System.out.println(result);
		 //JSONObject json = JSON.parseObject(result);
		 //System.out.println(json.get("username"));
		 //String username = (String)json.get("username");
		 //request.setAttribute("username",username);
		 //System.out.println(username);
		// return result;
		 return "showMembers";
		 }
		
	//在团队中添加成员
	@RequestMapping("/addmember")
	public String addmember() {

		return "addMember";
	}
	
	//获取成员信息
		@RequestMapping("/studentpage")
		 public String studentinfo(HttpServletRequest request,String username,Model model) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
		 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
		 
					 //.execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
					 .execute("localhost",3000,"/api/v1/teams/5/members/"+username+"","get","");
			 JSONObject json = JSON.parseObject(result);
			 System.out.println(json.get("username"));
			 System.out.println(json.get("id"));
			 String uname = (String)json.get("username");
			 int uid = (int)json.get("id");
			 request.setAttribute("username",uname);
			 request.setAttribute("uid",uid);
		 //return result;
				return "s_page";
				
				//JSONObject json = JSON.parseObject(result);
				 //System.out.println(json.get("username"));
				 //String username = (String)json.get("username");
				 //request.setAttribute("username",username);
				 //System.out.println(username);
		 }
		
		//添加仓库
		@RequestMapping("/addrepos")
		public String addrepos() {

			return "addRepos";
		}
		
		@RequestMapping("/addRepos")
		 public String addRepos(HttpServletRequest request,String username,String name,String description) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
		 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
					 .execute("localhost",3000,"/api/v1/admin/users/"+username+"/repos","post","{\"descripotion\":\""+description+"\",\"name\":\""+name+"\",\"gitignores\":\"\",\"license\":\"\",\"readme\":\"\"}");
					// .execute("localhost",3000,"/api/v1/orgs/"+id+"/teams","post","{\"description\":\""+description+"\",\"name\":\""+name+"\",\"permission\":\"read\",\"units\":[\"\"]}");
						
		 System.out.println(result);
		 //JSONObject json = JSON.parseObject(result);
		 //System.out.println(json.get("username"));
		 //String username = (String)json.get("username");
		 //request.setAttribute("username",username);
		 //System.out.println(username);
		// return result;
		 return "showMembers";
		 }
		
		
		//获取班级列表
		@RequestMapping("/e_classlist")
		public String e_classlist(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "e_classList";
		}
		
		@RequestMapping("/e_classlist2")
		public String e_classlist2(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "e_classList2";
		}
		@RequestMapping("/e_classlist3")
		public String e_classlist3(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "e_classList3";
		}
		@RequestMapping("/e_classlist4")
		public String e_classlist4(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "e_classList4";
		}
		
		@RequestMapping("/s_classlist")
		public String s_classlist(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "s_classList";
		}
		@RequestMapping("/s_classlist2")
		public String s_classlist2(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "s_classList2";
		}
		
		@RequestMapping("/g_classlist")
		public String g_classlist(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","wy","123456")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute("localhost",3000,"/api/v1/admin/orgs","get","");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "g_classList";
		}
		
		//获取成员
		@RequestMapping("/e_getmembers")
		 public String e_getMembers(String username,Model model) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute("localhost",3000,"/api/v1/orgs/"+username+"/members","get","");
			 String result2 = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute("localhost",3000,"/api/v1/orgs/"+username+"/teams","get","");
			 	System.out.println(result);
				List<Member> list = JSONObject.parseArray(result,Member.class);
				model.addAttribute("list",list);
				
				List<Team> list2 = JSONObject.parseArray(result2,Team.class);
				model.addAttribute("list2",list2);
		 //return result;
				return "e_showMembers";
		 }
		
		@RequestMapping("/s_getmembers")
		 public String s_getMembers(String username,Model model) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute("localhost",3000,"/api/v1/orgs/"+username+"/members","get","");
			 String result2 = api.createBuilder()
					 .addAuthenticator("httpbasic","wy","123456")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute("localhost",3000,"/api/v1/orgs/"+username+"/teams","get","");
			 	System.out.println(result);
				List<Member> list = JSONObject.parseArray(result,Member.class);
				model.addAttribute("list",list);
				
				List<Team> list2 = JSONObject.parseArray(result2,Team.class);
				model.addAttribute("list2",list2);
		 //return result;
				return "s_showMembers";
		 }
		
	/*
	 * //添加仓库
	 * 
	 * @RequestMapping("/addexam") public String addexam() {
	 * 
	 * return "examPage"; }
	 */
		
		
		//发布签到
		
		@RequestMapping("/addsign")
		 public String addsign() {
			
	        return "addSign";	
			
		 }
		
		@RequestMapping("/addSign")
		 public String addsign(Model model,Sign sign,String className,String name,int stotal,SignStatus signStatus) {
			if(sign != null){
				signService.addSign(sign);
				int id = sign.getId();
				for(int i = 1;i<=stotal;i++) {
					//String s = Integer.toString(i);
					if(i<10) {
						String s = className + 0 + i;
						signStatus.setSignId(id);
						signStatus.setSignName(name);
						signStatus.setUsername(s);
						signStatus.setClassName(className);
						signService.addSignInitial(id,className,signStatus);
					}else {
						String s = className + i;
						signStatus.setSignId(id);
						signStatus.setSignName(name);
						signStatus.setUsername(s);
						signStatus.setClassName(className);
						signService.addSignInitial(id,className,signStatus);
					}
					
				/*
				 * signStatus.setSignId(id); signStatus.setSignName(name);
				 * signStatus.setUsername(s); signStatus.setClassName(className);
				 * signService.addSignInitial(id,className,signStatus);
				 */
				}
	        }
	        return "redirect:/signlist";	
			
		 }
		
		@RequestMapping("/addSignStatus")
		 public String addsignStatus(Model model,SignStatus signStatus) {
			if(signStatus != null){
				signService.addSignStatus(signStatus);
				
	        }
	        //return "redirect:/signStatuslist";	
			return "redirect:/signstatuslist";
		 }
		
		@RequestMapping("/updateSignStatus")
		 public String updatesignStatus(HttpServletRequest request,Model model,SignStatus signStatus,String username,String signName,String seatnum) {
			if(signService.updateSignStatus(signStatus)){  
	            signStatus = signService.findSSByUS(username,signName);  
	            signStatus.setSeatnum(seatnum);
	            signStatus.setSignName(signName);
	            request.setAttribute("signStatus", signStatus);  
	            model.addAttribute("signStatus", signStatus);  
	            return "redirect:/signstatuslist";  
	        }else{  
	            return "fail";  
	        }  
			
		 }
		
		@RequestMapping(value="/updateSS",produces = "application/json;charset=UTF-8")

	    @ResponseBody
		 public String updateSS(HttpServletRequest request,Model model,HttpServletResponse response,@RequestBody SignStatus signStatus) {
			response.setContentType("text/html;charset=utf-8");
	        /*设置响应头允许ajax跨域访问*/
	        response.setHeader("Access-Control-Allow-Origin", "*");
	 
	        /* 星号表示所有的异域请求都可以接受， */
	        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
	        //获取微信小程序get的参数值并打印
	        String username = signStatus.getUsername();
	        String seatnum = signStatus.getSeatnum();
	        String signname = signStatus.getSignName();
	        int status = signStatus.getStatus();
	        //String seatnum = request.getParameter("seatnum");
			System.out.println(username);
			System.out.println(seatnum);
			
			if(signService.updateSignStatus(signStatus)){  
	            signStatus = signService.findSSByUS(username,signname);  
	            signStatus.setSeatnum(seatnum);
	            signStatus.setSignName(signname);
	            request.setAttribute("signStatus", signStatus);  
	            model.addAttribute("signStatus", signStatus);  
	            return "redirect:/signstatuslist";  
	        }else{  
	            return "fail";  
	        }  
			
		 }
		
	/*
	 * @RequestMapping("/addSignInitial") public String addsignInitial(Model
	 * model,SignStatus signStatus,String classname) { if(signStatus != null){
	 * for(int i = 0;i<30;i++) { signStatus.setClassName(classname);
	 * signService.addSignInitial(classname,signStatus); } }
	 * 
	 * return "redirect:/signstatuslist"; }
	 */
		//签到列表
		@RequestMapping("/signlist") 
		 public String signlist(HttpServletRequest request,Model model) {
			signService.showSignByPage(request,model);
			 	//Sign sign = userService.findSignByClassname(username);
			 	//List<Sign> signlist = userService.findSignByClassname();
			 	//model.addAttribute("signlist",signlist);
			return "signList";
		 }
		@RequestMapping("/signstatuslist") 
		 public String signstatuslist(HttpServletRequest request,Model model) {
			signService.showSignStatusByPage(request,model);
			 	//Sign sign = userService.findSignByClassname(username);
			 	//List<Sign> signlist = userService.findSignByClassname();
			 	//model.addAttribute("signlist",signlist);
			return "signStatusList";
		 }
		
		@RequestMapping("/s_signlist") 
		 public String s_signliist(HttpServletRequest request,Model model) {
			signService.showSignByPage(request,model);
			return "s_signList";
		 }
		
		@RequestMapping("/signByClass") 
		 public String signByClass(String classname,HttpServletRequest request,Model model) {
			signService.showSignByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "signByClass";
		 }
		@RequestMapping("/signlistByClass") 
		 public String signlistByClass(String classname,HttpServletRequest request,Model model) {
			signService.showSignByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "signlistByClass";
		 }
		
		@RequestMapping("/signlistByClass2") 
		 public String signlistByClass2(String classname,HttpServletRequest request,Model model) {
			signService.showSignByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "signlistByClass2";
		 }
		
		@RequestMapping("/findabsent") 
		 public String findabsent(int id,HttpServletRequest request,Model model) {
			signService.showAbsent(id,request,model);
			request.setAttribute("sid",id);
			return "unabsent";
		 }
		
		@RequestMapping("/mysignstatus") 
		 public String findMySignStatus(String username,HttpServletRequest request,Model model) {
			
			signService.showSSByUsername(username,request,model);
			request.setAttribute("username",username);
			return "myabsent";
		 }
		
	/*
	 * @RequestMapping("/sign_in") public String signIn(Model model,int id) {
	 * model.addAttribute("sign", signService.findSignById(id)); return "sign_in"; }
	 */
		@RequestMapping("/sign_in")
		public String signIn(Model model,int id) {
			model.addAttribute("sign", signService.findSignById(id));  
			return "sign_in";
		}
		@RequestMapping("/getSSByUS")
		public String getSSByUS(HttpServletRequest request,Model model,String username,String signName,int roomtype) {
			request.setAttribute("signname",signName);
			request.setAttribute("signStatus",signService.findSSByUS(username,signName));
			model.addAttribute("signStatus", signService.findSSByUS(username,signName));  
			if(roomtype == 1) {
				return "sign_in1";
			}
			else {
				return "sign_in2";
			}
		}
		
		//签到统计分析
		@RequestMapping("/signstatistics")
		 public String signstatistics(HttpServletRequest request,String signname,int id,String classname) {
			int signIn = (int) signDao.getCountSign(id,1);
			int signOut = (int) signDao.getCountSign(id,0);
			
			request.setAttribute("signIn",signIn);
			request.setAttribute("signOut",signOut);
			request.setAttribute("classname",classname);
	        return "signStatistics";	
			
		 }
		
		
		//获取考试列表
		@RequestMapping("/examlist") 
		 public String examlist(HttpServletRequest request,Model model,String classname) {
			examService.showExamByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "t_examList";
		 }
		
		@RequestMapping("/examlist2") 
		 public String examlist2(HttpServletRequest request,Model model,String classname) {
			examService.showExamByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "t_examList2";
		 }
		
		@RequestMapping("/examlist3") 
		 public String examlist3(HttpServletRequest request,Model model,String classname) {
			examCloseService.showECloseByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "t_examList3";
		 }
		
		
		@RequestMapping("/examlistByClass") 
		 public String examlistByClass(HttpServletRequest request,Model model,String classname) {
			examService.showExamByClassname(classname,request,model);
			request.setAttribute("classname",classname);
			return "s_examList";
		 }
		
		//新增考试内容
		@RequestMapping("/addexam")
		public String addexam(Model model,int examtype) {
			if(examtype == 1) {
				return "addExam";
			}else {
				return "addExam2";
			}
		}
		
		@RequestMapping("/addExam")
		 public String addexam(Model model,Exam exam,Grade grade,String examName,String classname,String coursename,int stotal) {
			if(exam != null){
				examService.addExam(exam);
				for(int i=1;i<=stotal;i++) {
					if(i<10) {
						String num = classname + 0 + i;
						grade.setExamname(examName);
						grade.setClassname(classname);
						grade.setCoursename(coursename);
						grade.setUsername(num);
						grade.setExamstatus(0);
						grade.setGrade(0);
						gradeService.addGrade(grade);
					}else {
						String num = classname + i;
						grade.setExamname(examName);
						grade.setClassname(classname);
						grade.setCoursename(coursename);
						grade.setUsername(num);
						grade.setExamstatus(0);
						grade.setGrade(0);
						gradeService.addGrade(grade);
					}
				}
	        }
	        //return "redirect:/signStatuslist";	
			return "redirect:/examlist";
		 }
		
		@RequestMapping("/addExam2")
		 public String addexam2(Model model,ExamClose examClose,Grade grade,String examName,String classname,String coursename,int stotal) {
			
			if(examClose != null){
				examCloseService.addExamClose(examClose);
				for(int i=1;i<=stotal;i++) {
					if(i<10) {
						String num = classname + 0 + i;
						grade.setExamname(examName);
						grade.setClassname(classname);
						grade.setCoursename(coursename);
						grade.setUsername(num);
						grade.setExamstatus(0);
						grade.setGrade(0);
						gradeService.addGrade(grade);
					}else {
						String num = classname + i;
						grade.setExamname(examName);
						grade.setClassname(classname);
						grade.setCoursename(coursename);
						grade.setUsername(num);
						grade.setExamstatus(0);
						grade.setGrade(0);
						gradeService.addGrade(grade);
					}
				}
	        }
	        //return "redirect:/signStatuslist";	
			return "redirect:/success";
		 }
		
		
		//获取试卷列表
		@RequestMapping("/paperlist")
		public String getpaperlist(Model model) {
			
			List<PaperInfo> plist = userService.findAllPaper();
			model.addAttribute("paperList",plist);
			return "showpapers";
		 }
		
		//获取试卷内容
	/*
	 * @RequestMapping("/papercontent/{pid}") public ModelAndView
	 * getpapercontent(@PathVariable("pid") int pid) { ModelAndView model = new
	 * ModelAndView(); model.setViewName("/userLogin/papercontent"); PaperContent
	 * papercontent = userService.findPaperContent(pid); model.addObject("pcontent",
	 * papercontent);
	 * 
	 * return model; }
	 */
		@RequestMapping("/papercontent")
		public String getpapercontent(Model model) {
		/*
		 * ModelAndView model = new ModelAndView();
		 * model.setViewName("/userLogin/papercontent");
		 */
			List<PaperContent> papercontent = userService.findPaperContent();
			model.addAttribute("pcontent", papercontent);
			
			return "papercontent";
		 }	
		
		@RequestMapping("/addquestion")
		public String addquestion(Model model) {
			return "addQuestion";
		}
		//发布签到
		@RequestMapping("/addpaper")
		public String addpaper(Model model) {
			return "addPaper";
		}
		
		//学习情况统计
		@RequestMapping("/studystatistics")
		 public String studystatistics(HttpServletRequest request,String username,String coursename) {
			
			int workIn = (int) countDao.getCountWorkStatus(username,coursename,1);
			int workOut = (int) countDao.getCountWorkStatus(username,coursename,0);
			
			int signIn = (int) countDao.getCountSignStatus(username,coursename,1);
			int signOut = (int) countDao.getCountSignStatus(username,coursename,0);
			
			int gradeIn = (int) countDao.getCountExamScore(username,coursename,60);
			int g9 = (int) countDao.getCountExamScore(username,coursename,90);
			int g8 = (int) countDao.getCountExamScore(username,coursename,80);
			int g7 = (int) countDao.getCountExamScore(username,coursename,70);
			int gradeStotal = (int) countDao.getCountExamStotal(username,coursename);
			int gradeOut = gradeStotal - gradeIn;
			
			request.setAttribute("workIn",workIn);
			request.setAttribute("workOut",workOut);
			request.setAttribute("signIn",signIn);
			request.setAttribute("signOut",signOut);
			request.setAttribute("gradeIn",gradeIn);
			request.setAttribute("gradeOut",gradeOut);
			request.setAttribute("g9",g9);
			request.setAttribute("g8",g8);
			request.setAttribute("g7",g7);
			request.setAttribute("username",username);
			request.setAttribute("coursename",coursename);
	       return "studyStatistics";	
			
		 }
		
		//学习情况统计
				@RequestMapping("/studystatistics2")
				 public String studystatistics2(HttpServletRequest request,String username) {
					
					int workIn = (int) countDao.getCountWorkStatus2(username,1);
					int workOut = (int) countDao.getCountWorkStatus2(username,0);
					
					int signIn = (int) countDao.getCountSignStatus2(username,1);
					int signOut = (int) countDao.getCountSignStatus2(username,0);
					
					int gradeIn = (int) countDao.getCountExamScore2(username,60);
					int g9 = (int) countDao.getCountExamScore2(username,290);
					int g8 = (int) countDao.getCountExamScore2(username,80);
					int g7 = (int) countDao.getCountExamScore2(username,70);
					int gradeStotal = (int) countDao.getCountExamStotal2(username);
					int gradeOut = gradeStotal - gradeIn;
					
					request.setAttribute("workIn",workIn);
					request.setAttribute("workOut",workOut);
					request.setAttribute("signIn",signIn);
					request.setAttribute("signOut",signOut);
					request.setAttribute("gradeIn",gradeIn);
					request.setAttribute("gradeOut",gradeOut);
					request.setAttribute("g9",g9);
					request.setAttribute("g8",g8);
					request.setAttribute("g7",g7);
					request.setAttribute("username",username);
					
			       return "studyStatistics2";	
					
				 }
		
}

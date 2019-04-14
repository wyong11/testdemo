package com.sy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sy.entity.User;
import com.sy.entity.Class;
import com.sy.entity.Member;
import com.sy.entity.Team;
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

	// 正常访问login页面
	@RequestMapping("/login")
	public String login() {
		return "login";
	}

	// 表单提交过来的路径
	@RequestMapping("/checkLogin")
	public String checkLogin(User user, Model model) {
		// 调用service方法
		user = userService.checkLogin(user.getUsername(), user.getPassword());
		// 若有user则添加到model里并且跳转到成功页面
		if (user != null) {
			model.addAttribute("user", user);
			return "t_home";
		} 
		return "fail";
	}
	
	//注册
	@RequestMapping("/regist")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/doRegist")
    public String doRegist(User user,Model model){
        System.out.println(user.getUsername());
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
			.addAuthenticator("httpbasic","teacher","teacher")
			.addSSL(new GiteaSSLFactory())
			.buildClient()
			//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934838@qq.com\", \"username\":\"zqwwr\",\"password\":\"123456\"}");
		
			.execute2("localhost",3000,"/api/v1/admin/orgs","get");
			
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
				.addAuthenticator("httpbasic","teacher","teacher")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				//.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934838@qq.com\", \"username\":\"zqwwr\",\"password\":\"123456\"}");
			
				.execute2("localhost",3000,"/api/v1/admin/orgs","get");
				
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

	@RequestMapping("/worklist")
	public String worklist() {
		
		return "workList";
	}
		
	@RequestMapping("/home")
	public String hrefpage2() {

		return "gohome";
	}

	@RequestMapping("/addwork")
	public String addwork() {

		return "addWork";
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

	@RequestMapping("/signlist")
	public String hrefpage6() {

		return "signList";
	}

	@RequestMapping("/addexam")
	public String addexam(String url,String localurl,HttpServletRequest request) throws IOException {
		/*
		 * GitUtil gitUtil = new GitUtil(); gitUtil.cloneRepository(url,localurl); File
		 * file = new File(localurl);
		 */
		File file = new File("C:/Users/wyong/Desktop/copy/copy.java");
		FileReader fileReader = new FileReader(file);  
        BufferedReader bReader = new BufferedReader(fileReader);  
          
        String tempString = null;  
        while ( (tempString = bReader.readLine()) != null ) {  
            System.out.println(tempString);  
        }  
        
        bReader.close();  
        fileReader.close();  
   	
   	 	request.setAttribute("tempString",tempString);
        
		return "addExam";
	}

	@RequestMapping("/examlist")
	public String hrefpage8() {

		return "examList";
	}
	
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

	@RequestMapping(value = "/emp01", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public String empAdd02(@RequestBody List<User> list) {
		for (User e : list) {
			// employeeMapper.insert(e);
			try {
				Object user = userService.insertSalesChannel(list);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return "{\"msg\":\"success\"}";
	}

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
				 .addAuthenticator("httpbasic","teacher","teacher")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
	 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
	 
				 .execute2("localhost",3000,"/api/v1/orgs/162011","get");
	 
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
				 .addAuthenticator("httpbasic","teacher","teacher")
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
			.addAuthenticator("httpbasic","teacher","teacher")
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
				 .addAuthenticator("httpbasic","teacher","teacher")
				 .addSSL(new GiteaSSLFactory()) .buildClient()
				 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
		 String result2 = api.createBuilder()
				 .addAuthenticator("httpbasic","teacher","teacher")
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
					 .addAuthenticator("httpbasic","teacher","teacher")
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
					 .addAuthenticator("httpbasic","teacher","teacher")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
		 //.execute("localhost",3000,"/api/v1/admin/users","post","{\"email\":\"2538934830@qq.com\", \"username\":\"zqw\",\"password\":\"123456\"}");
		 
					 //.execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
					 .execute2("localhost",3000,"/api/v1/teams/5/members/"+username+"","get");
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
					 .addAuthenticator("httpbasic","teacher","teacher")
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
				.addAuthenticator("httpbasic","teacher","teacher")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute2("localhost",3000,"/api/v1/admin/orgs","get");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "e_classList";
		}
		
		@RequestMapping("/s_classlist")
		public String s_classlist(Model model) {
			RestApi api = new GiteaRestApi();
			String result = api.createBuilder()
				.addAuthenticator("httpbasic","teacher","teacher")
				.addSSL(new GiteaSSLFactory())
				.buildClient()
				.execute2("localhost",3000,"/api/v1/admin/orgs","get");
				System.out.println(result);
				List<Class> list = JSONObject.parseArray(result,Class.class);
				model.addAttribute("list",list);
			return "s_classList";
		}
		
		//获取成员
		@RequestMapping("/e_getmembers")
		 public String e_getMembers(String username,Model model) {
			 RestApi api = new GiteaRestApi();
			 String result = api.createBuilder()
					 .addAuthenticator("httpbasic","teacher","teacher")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
			 String result2 = api.createBuilder()
					 .addAuthenticator("httpbasic","teacher","teacher")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/teams","get");
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
					 .addAuthenticator("httpbasic","teacher","teacher")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/members","get");
			 String result2 = api.createBuilder()
					 .addAuthenticator("httpbasic","teacher","teacher")
					 .addSSL(new GiteaSSLFactory()) .buildClient()
					 .execute2("localhost",3000,"/api/v1/orgs/"+username+"/teams","get");
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
		 public String addsign(Model model) {
			 
				
				
				return "s_showMembers";
		 }
		
}

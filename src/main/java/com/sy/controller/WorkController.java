package com.sy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jgit.api.errors.GitAPIException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sy.dao.WorkDao;
import com.sy.entity.Files;
import com.sy.entity.Work;
import com.sy.entity.WorkStatus;
import com.sy.giteaapi.GitUtil;
import com.sy.giteaapi.JgitTest;
import com.sy.service.CourseService;
import com.sy.service.WorkService;

@Controller
public class WorkController {

	@Autowired
	private WorkService workService;
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private WorkDao workDao;
	
	@RequestMapping("/studentlist") 
	 public String studentByClassname(String classname,String usename,HttpServletRequest request,Model model) {
		
		workService.showStudentByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		return "studentList";
	 }
	
	@RequestMapping("/studentlist2") 
	 public String studentByClassname2(String classname,String coursename,HttpServletRequest request,Model model) {
		
		workService.showStudentByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		request.setAttribute("coursename",coursename);
		return "studentList2";
	 }
	@RequestMapping("/studentlist3") 
	 public String studentByClassname3(String classname,String coursename,HttpServletRequest request,Model model) {
		
		workService.showStudentByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		request.setAttribute("coursename",coursename);
		return "studentList3";
	 }
	
	@RequestMapping("/copyfiles")
	public String addexam0(String classname,String username,Files files,HttpServletRequest request) throws IOException {
		int deep=0;
		request.getSession().setAttribute("classname",classname);
		request.getSession().setAttribute("username",username);	
		
		GitUtil gitUtil = new GitUtil();
			String url = "http://localhost:3000/"+username+"/"+username+".git";
	        String localPath = "E:/CloneRepo/"+classname+"/"+username+"";
	        gitUtil.cloneRepository(url,localPath);
	        File file = new File(localPath);   
	        // 获得该文件夹内的所有文件   
	        File[] array = file.listFiles();   

	        for(int i=0;i<array.length;i++)
	        {   
	        	files.setClassname(classname);
		        files.setUsername(username);
		        
	            if(array[i].isFile())//如果是文件
	            {   
	                    for (int j = 0; j < deep; j++)//输出前置空格
	                    System.out.print(" ");
	                // 只输出文件名字  
	                System.out.println( array[i].getName()); 
	                files.setFilename(array[i].getName());
	                File filepath = new File("E:/CloneRepo/"+classname+"/"+username+"/"+array[i].getName()+"");
	                FileReader fileReader = new FileReader(filepath);  
	    	        BufferedReader bReader = new BufferedReader(fileReader);  
	    	        StringBuilder texts =new StringBuilder();   
	    	        //String tempString = null; 
	    	        String line =null;
	    	        while ( (line = bReader.readLine()) != null ) {  
	    	        	texts.append(line+"\n");  
	    	            //System.out.print(line);
	    	           // System.out.println(tempString);  
	    	        }  
	    	        System.out.println(texts.toString());
	    	        files.setFilecontent(texts.toString());
	    	        request.getSession().setAttribute("tempString",texts.toString());
	    	        workService.addWorkFiles(files);
	    	        bReader.close();  
	    	        fileReader.close();  
	    	        
	                
	                // 输出当前文件的完整路径   
	               // System.out.println("#####" + array[i]);   
	                
	               // System.out.println(array[i].getPath());   
	            }
	            else if(array[i].isDirectory())//如果是文件夹
	            {  
	                    for (int j = 0; j < deep; j++)//输出前置空格
	                    System.out.print(" ");

	                    System.out.println( array[i].getName());
	                    //System.out.println(array[i].getPath());
	                    //文件夹需要调用递归 ，深度+1
	                //getFile(array[i].getPath(),deep+1);  
	            }   
	        }   
	        
	        return "success";
	        
	        
	        
	        
		/*
		 * File file = new
		 * File("E:/CloneRepo/"+classname+"/"+username+"/"+username+".java"); String
		 * filename = username + ".java"; gitUtil.cloneRepository(url,localPath);
		 * FileReader fileReader = new FileReader(file); BufferedReader bReader = new
		 * BufferedReader(fileReader); StringBuilder texts =new StringBuilder();
		 * //String tempString = null; String line =null; // StringBuilder sb = new
		 * StringBuilder(); files.setClassname(classname); files.setUsername(username);
		 * files.setFilename(filename); while ( (line = bReader.readLine()) != null ) {
		 * texts.append(line+"\n"); //System.out.print(line); //
		 * System.out.println(tempString); } System.out.println(texts.toString());
		 * files.setFilecontent(texts.toString());
		 * request.getSession().setAttribute("tempString",texts.toString());
		 * examService.addFiles(files);
		 * 
		 * bReader.close(); fileReader.close();
		 * 
		 * return "beginExam";
		 */
	}
	@RequestMapping("/workfileslist") 
	 public String workfilesByUsername(String username,HttpServletRequest request,Model model) {
		
		workService.showFilesByUsername(username,request,model);
		request.setAttribute("username",username);
		return "workfilesList";
	 }
	
	@RequestMapping("/filecontent") 
	 public String filecontentByUsername(String username,String filename,HttpServletRequest request,Model model) {
		
		request.setAttribute("files",workService.findFCByUF(username,filename));
		model.addAttribute("files", workService.findFCByUF(username,filename)); 
		request.setAttribute("username",username);
		return "fileContent";
	 }
	
	@RequestMapping("/courseByClass") 
	 public String courseByClassname(String classname,HttpServletRequest request,Model model) {
		
		courseService.showCourseByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		return "courselist";
	 }
	
	@RequestMapping("/courseByName") 
	 public String courseByName(String teacher,HttpServletRequest request,Model model) {
		
		courseService.showCourseByTname(teacher,request,model);
		request.setAttribute("teacher",teacher);
		return "courselist2";
	 }
	
	@RequestMapping("/courseByName2") 
	 public String courseByName2(String teacher,HttpServletRequest request,Model model) {
		
		courseService.showCourseByTname(teacher,request,model);
		request.setAttribute("teacher",teacher);
		return "courselist3";
	 }
	@RequestMapping("/courseByName3") 
	 public String courseByName3(String teacher,HttpServletRequest request,Model model) {
		
		courseService.showCourseByTname(teacher,request,model);
		request.setAttribute("teacher",teacher);
		return "courselist4";
	 }
	
	@RequestMapping("/workByCC") 
	 public String workByCC(String classname,String coursename,HttpServletRequest request,Model model) {
		
		workService.showWorkByCC(classname,coursename,request,model);
		request.setAttribute("classname",classname);
		request.setAttribute("coursename",coursename);
		return "worklistByCC";
	 }
	@RequestMapping("/workByCC2") 
	 public String workByCC2(String classname,String coursename,int stotal,HttpServletRequest request,Model model) {
		
		workService.showWorkByCC(classname,coursename,request,model);
		request.getSession().setAttribute("classname",classname);
		request.getSession().setAttribute("coursename",coursename);
		request.getSession().setAttribute("stotal",stotal);
		return "worklistByCC2";
	 }
	@RequestMapping("/workByCC3") 
	 public String workByCC3(String classname,String coursename,HttpServletRequest request,Model model) {
		
		workService.showWorkByCC(classname,coursename,request,model);
		request.setAttribute("classname",classname);
		request.setAttribute("coursename",coursename);
		return "worklistByCC3";
	 }
	
	@RequestMapping("/workstatuslist") 
	 public String workstatuslist(String classname,String workname,HttpServletRequest request,Model model) {
		
		workService.showWSByCW(classname,workname,request,model);
		request.setAttribute("classname",classname);
		request.setAttribute("workname",workname);
		return "workstatusList";
	 }
	
	//发布作业
	@RequestMapping("/addwork")
	public String addexam(Model model) {
		return "addWork";
	}
	
	@RequestMapping("/addWork")
	 public String addwork(Model model,Work work,WorkStatus workStatus,String workname,String classname,String coursename,int stotal) {
		if(work != null){
			workService.addWork(work);
			for(int i=1;i<=stotal;i++) {
				if(i<10) {
					String num = classname + 0 + i;
					workStatus.setWorkname(workname);
					workStatus.setClassname(classname);
					workStatus.setCoursename(coursename);
					workStatus.setUsername(num);
					workStatus.setStatus(0);
					workService.addWorkStatus(workStatus);
				}else {
					String num = classname + i;
					workStatus.setWorkname(workname);
					workStatus.setClassname(classname);
					workStatus.setCoursename(coursename);
					workStatus.setUsername(num);
					workStatus.setStatus(0);
					workService.addWorkStatus(workStatus);
				}
				
			}
			
			 
       }
       //return "redirect:/signStatuslist";	
		return "redirect:/addwork";
	 }
	
	
	@RequestMapping("/commitFile")
	public String commitFile(Model model,HttpServletRequest request){
		String fileName = (String) request.getAttribute("workfile");
		System.out.println(fileName);
        if (fileName != null && fileName.trim().length() > 0) {
            File file = new File(fileName);
            file.getPath();
            System.out.println("文件路径："+file);
        }else {
        	System.out.println("空值");
        }
		return "";
	}
	/*
	 * @RequestMapping("commitFile") public ModelAndView
	 * editProfile(@RequestParam(value="file",required=false)MultipartFile
	 * file,HttpServletRequest request){ //String file=request.getParameter("file");
	 * System.out.println(file); ModelAndView mv=new ModelAndView(); return mv; }
	 */
	/**
	 * 解析新建编辑
	 * 
	 * @param String
	 *            newThingsParse是一个拥有ThingsParse类结构的json字符串
	 * @return
	 */
	@RequestMapping(value = "/commitFile2")
	public ModelAndView getfilepath(MultipartFile file,String note,String workname,HttpServletRequest request,WorkStatus workStatus) {
		String username = (String) request.getSession().getAttribute("username");
		System.out.println(username);
		String password = "123456";
		
		String filePath = "";
		if (!file.isEmpty()) {
			File temp = new File("");
			filePath = temp.getAbsolutePath() + "\\" + file.getOriginalFilename();
			System.out.println("文件路径："+filePath);
			JgitTest jt = new JgitTest();
			try {
				jt.gitAdd(filePath);
				jt.gitCommit(note);
				jt.gitPush(username,password);
				if(workService.updateWorkStatus(workStatus)) {
					workStatus = workService.findWSByUW(username,workname);  
					
					workStatus.setStatus(1);
					return new ModelAndView("success");
				}else {
					return new ModelAndView("fail");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (GitAPIException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}else {
			System.out.println("空值");
		}
		return new ModelAndView("success");//后台重定向
	}
	
	@RequestMapping("/success")
	public String doSeccess(Model model) {
		return "success";
	}
	
	//作业提交统计
	@RequestMapping("/workstatistics")
	 public String workstatistics(HttpServletRequest request,String classname,String workname) {
		int workIn = (int) workDao.getWSByCW(classname,workname,1);
		int workOut = (int) workDao.getWSByCW(classname,workname,0);
		
		request.setAttribute("workIn",workIn);
		request.setAttribute("workOut",workOut);
		request.setAttribute("classname",classname);
		request.setAttribute("workname",workname);
       return "workStatistics";	
		
	 }
	
	//我的作业完成情况
	@RequestMapping("/myworkstatus") 
	 public String workstatusByUsername(String username,HttpServletRequest request,Model model) {
		
		workService.showWSByUsername(username,request,model);
		request.setAttribute("username",username);
		
		return "myWorkStatus";
	 }
	
}

package com.sy.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sy.dao.ExamDao;
import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.ExamClose;
import com.sy.entity.Files;
import com.sy.entity.Grade;
import com.sy.entity.Subject;
import com.sy.giteaapi.GitUtil;
import com.sy.service.ExamCloseService;
import com.sy.service.ExamService;
import com.sy.service.GradeService;

@Controller
public class ExamController {
	
	@Autowired
	private ExamService examService;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private ExamCloseService examCloseService;
	
	@Autowired
	private ExamDao examDao;
	
	@RequestMapping("/joinexam")
	public String addexam(String examname,String classname,String username,String coursename,Files files,HttpServletRequest request) throws IOException {
		request.getSession().setAttribute("examname",examname);
		request.getSession().setAttribute("classname",classname);
		request.getSession().setAttribute("username",username);	
		request.getSession().setAttribute("coursename",coursename);
		GitUtil gitUtil = new GitUtil();
			String url = "http://localhost:3000/"+username+"/"+username+".git";
	        String localPath = "E:/CloneRepo/"+classname+"/"+username+"";
			File file = new File("E:/CloneRepo/"+classname+"/"+username+"/"+username+".java");
			String filename = username + ".java";
			gitUtil.cloneRepository(url,localPath);
			FileReader fileReader = new FileReader(file);  
	        BufferedReader bReader = new BufferedReader(fileReader);  
	        StringBuilder texts =new StringBuilder();   
	        //String tempString = null; 
	        String line =null;
	        while ( (line = bReader.readLine()) != null ) {  
	        	texts.append(line);  
	        	files.setClassname(classname);
				files.setUsername(username);
				files.setFilename(filename);
	        	files.setFilecontent(line);
	        	examService.addFiles(files);
	            //System.out.print(line);
	           // System.out.println(tempString);  
	            request.setAttribute("tempString",line);
	        }  
	        
	        bReader.close();  
	        fileReader.close();  
        
		return "beginExam";
	}
	@RequestMapping("/beginexam") 
	 public String beginExam(String username,String examname,HttpServletRequest request,Model model) {
		examService.showPaperByUsername(username,request,model);
		request.setAttribute("grade",gradeService.findGradeByUE(username,examname));
		model.addAttribute("grade",gradeService.findGradeByUE(username,examname));
		request.setAttribute("username",username);
		request.setAttribute("examname",examname);
		return "doPaper";
	 }
	
	//提交试卷答案&得出考试成绩
	@RequestMapping("/addanswers")
	 public String addAnswers(String examname,String username,int examstatus,int score,String a1,HttpServletRequest request,Model model,Answers answers,Grade grade) {
		score = 0;
		System.out.println(a1);
		if(answers != null){
			examService.addAnswers(answers);
			
			String a2 = answers.getAnswers(); 
			score = gradeService.getGrade(a1,a2);
			grade.setGrade(score);
			if(gradeService.updateGrade(grade)) {
				grade = gradeService.findGradeByUE(username,examname);  
				
				grade.setExamstatus(1);
				return "redirect:/mygrade";  
			}else {
				return "fail";
			}
	            
       }
       //return "redirect:/signStatuslist";	
		return "redirect:/examlist";
	 }
	
	@RequestMapping("/mygrade") 
	 public String gradeByUsername(String username,HttpServletRequest request,Model model) {
		
		gradeService.showGradeByUsername(username,request,model);
		request.setAttribute("username",username);
		return "myGrades";
	 }
	@RequestMapping("/gradelist") 
	 public String gradeByClassname(String classname,HttpServletRequest request,Model model) {
		
		gradeService.showGradeByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		return "gradelist";
	 }
	@RequestMapping("/classgrade") 
	 public String gradeByCE(String classname,String examname,HttpServletRequest request,Model model) {
		
		gradeService.showGradeByCE(classname,examname,request,model);
		request.setAttribute("classname",classname);
		return "classgrade";
	 }
	
	@RequestMapping("/joinexam0")
	public String addexam0(String examname,String classname,String username,String coursename,Files files,HttpServletRequest request) throws IOException {
		request.getSession().setAttribute("examname",examname);
		request.getSession().setAttribute("classname",classname);
		request.getSession().setAttribute("coursename",coursename);
		GitUtil gitUtil = new GitUtil();
			String url = "http://localhost:3000/"+username+"/"+username+".git";
	        String localPath = "E:/CloneRepo/"+classname+"/"+username+"";
			File file = new File("E:/CloneRepo/"+classname+"/"+username+"/"+username+".java");
			String filename = username + ".java";
			gitUtil.cloneRepository(url,localPath);
			FileReader fileReader = new FileReader(file);  
	        BufferedReader bReader = new BufferedReader(fileReader);  
	        StringBuilder texts =new StringBuilder();   
	        String line =null;
	        files.setClassname(classname);
	        files.setUsername(username);
	        files.setFilename(filename);
	        while ( (line = bReader.readLine()) != null ) {  
	        	texts.append(line+"\n");  
	        }  
	        System.out.println(texts.toString());
	        files.setFilecontent(texts.toString());
	        request.getSession().setAttribute("tempString",texts.toString());
	        examService.addFiles(files);
	        bReader.close();  
	        fileReader.close();  
        
		return "beginExam";
	}
	
	@RequestMapping("/gradestatistics")
	 public String gradestatistics(HttpServletRequest request,String examname,String classname) {
		int examIn = (int) examDao.getCountGrade(examname,classname,1);
		int examOut = (int) examDao.getCountGrade(examname,classname,0);
		int stotal = (int) examDao.getStotal(examname,classname);
		int g1 = (int) examDao.getCountGradeU(examname,classname,90);
		int g2 = (int) examDao.getCountGradeU(examname,classname,80);
		int g3 = (int) examDao.getCountGradeU(examname,classname,70);
		int g4 = (int) examDao.getCountGradeU(examname,classname,60);
		System.out.println(g2);
		int g5 = stotal - g4;
		request.setAttribute("examIn",examIn);
		request.setAttribute("examOut",examOut);
		request.setAttribute("g1",g1);
		request.setAttribute("g2",g2);
		request.setAttribute("g3",g3);
		request.setAttribute("g4",g4);
		request.setAttribute("g5",g5);
		request.setAttribute("classname",classname);
		request.setAttribute("examname",examname);
		
       return "gradeStatistics";	
		
	 }
	
	@RequestMapping("/subjectByEid") 
	 public String subjectByEid(HttpServletRequest request,Model model,int examid) {
		examCloseService.showSubjectByEid(examid,request,model);
		request.setAttribute("examid",examid);
		return "subjectlistByid";
	 }
	@RequestMapping("/s_subjectByEid") 
	 public String subjectByEid2(HttpServletRequest request,Model model,int examid,String examname) {
		request.getSession().setAttribute("examname",examname);
		examCloseService.showSubjectByEid(examid,request,model);
		request.setAttribute("examid",examid);
		return "s_subjectlistByid";
	 }
	
	@RequestMapping("/addsubject")
	public String addexam(HttpServletRequest request,Model model,int examid) {
		request.getSession().setAttribute("examid",examid);
			return "addSubject";
		
	}
	
	@RequestMapping("/addSubject")
	 public String addSubject(Model model,Subject subject) {
		
		if(subject != null){
			examCloseService.addSubject(subject);
			
			
       }
       //return "redirect:/signStatuslist";	
		return "redirect:/success";
	 }
	
	@RequestMapping("/eCloseByClass") 
	 public String ecloselist(HttpServletRequest request,Model model,String classname) {
		examCloseService.showECloseByClassname(classname,request,model);
		request.setAttribute("classname",classname);
		return "t_ecloselist";
	 }
	
	@RequestMapping("/commitpaper")
	public String commitpaper(HttpServletRequest request,Model model,Grade grade,int examstatus,int score,String examname,String username) {
		
		System.out.println(username);
		System.out.println(examname);
		int sco = 0;
		String answer1 = request.getParameter("answer1");
		String answer2 = request.getParameter("answer2");
		String answer3 = request.getParameter("answer3");
		String a1 = request.getParameter("subject1");
		String a2 = request.getParameter("subject2");
		String a3 = request.getParameter("subject3");
		if(a1.equals(answer1)) {
			sco = sco + 2;
		}
		if(a2.equals(answer2)) {
			sco = sco + 2;
		}
		if(a3.equals(answer3)) {
			sco = sco + 2;
		}
		grade.setGrade(sco);
		if(gradeService.updateGrade(grade)) {
			grade = gradeService.findGradeByUE(username,examname);  
			
			grade.setExamstatus(1);
			return "success";
		}else {
			return "fail";
		}
	}

}

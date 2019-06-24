package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Files;
import com.sy.entity.Grade;
import com.sy.entity.Work;
import com.sy.entity.WorkStatus;

public interface WorkService {  

	void showWorkByPage(HttpServletRequest request,Model model); 
	void showWorkByCC(String classname,String coursename,HttpServletRequest request,Model model); 
	void showWorkByClassname(String classname,HttpServletRequest request,Model model); 
	void showStudentByClassname(String classname,HttpServletRequest request,Model model); 
	
	void showWSByUsername(String username,HttpServletRequest request,Model model); 
	void showWSByCW(String classname,String workname,HttpServletRequest request,Model model); 
	//获取文件名列表
	void showFilesByUsername(String username,HttpServletRequest request,Model model); 
	void addWork(Work work);
	void addWorkStatus(WorkStatus workStatus);
	boolean updateWorkStatus(WorkStatus workStatus);
	WorkStatus findWSByUW(String username,String workname);
	void addWorkFiles(Files files);
	void getFilesName(String path,int deep);
	
	Files findFCByUF(String username,String filename);
	
}

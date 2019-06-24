package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Grade;

public interface GradeService {

	void showGradeByPage(HttpServletRequest request,Model model); 
	void showGradeByClassname(String classname,HttpServletRequest request,Model model); 
	void showGradeByCE(String classname,String examname,HttpServletRequest request,Model model); 
	void showGradeByUsername(String username,HttpServletRequest request,Model model); 
	void addGrade(Grade grade);
	boolean updateGrade(Grade grade);
	Grade findGradeByUE(String username,String examname);
	int getGrade(String Str_1,String Str_2);
	
}

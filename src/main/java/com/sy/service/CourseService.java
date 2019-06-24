package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Course;

public interface CourseService {

	void showCourseByPage(HttpServletRequest request,Model model); 
	void showCourseByClassname(String classname,HttpServletRequest request,Model model); 
	void showCourseByTname(String teacher,HttpServletRequest request,Model model); 
	void addCourse(Course course);
	//boolean updateGrade(Grade grade);
	//Grade findGradeByUE(String username,String examname);
	
	
}

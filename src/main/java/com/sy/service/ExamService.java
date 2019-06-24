package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.Files;

public interface ExamService {

	void showExamByPage(HttpServletRequest request,Model model); 
	void showExamByClassname(String classname,HttpServletRequest request,Model model); 
	//获取试卷
	void showPaperByUsername(String username,HttpServletRequest request,Model model); 
	void addExam(Exam exam);
	void addFiles(Files files);
	void addAnswers(Answers answers);
}

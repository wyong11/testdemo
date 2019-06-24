package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Answers;
import com.sy.entity.ExamClose;
import com.sy.entity.Subject;

public interface ExamCloseService {
	void showECloseByPage(HttpServletRequest request,Model model); 
	void showECloseByClassname(String classname,HttpServletRequest request,Model model); 
	void showSubjectByEid(int examid,HttpServletRequest request,Model model); 
	void addExamClose(ExamClose examClose);
	void addSubject(Subject subject);
	void addAnswers(Answers answers);
}

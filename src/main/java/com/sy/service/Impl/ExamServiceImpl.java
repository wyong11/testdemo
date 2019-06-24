package com.sy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.ExamDao;
import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.ExamPage;
import com.sy.entity.Files;
import com.sy.entity.Page;
import com.sy.entity.Sign;
import com.sy.service.ExamService;

@Service
public class ExamServiceImpl implements ExamService {
	
	@Autowired
	private ExamDao examDao;

	@Override
	public void showExamByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Exam> exam = new ArrayList<Exam>(); 
      //查询用户总数
        int totalCount = (int) examDao.getExamCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            exam = this.examDao.findExamByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            exam = this.examDao.findExamByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("exam", exam); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showExamByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Exam> exam = new ArrayList<Exam>(); 
      //查询用户总数
        int totalCount = (int) examDao.getECByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            exam = this.examDao.findExamByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            exam = this.examDao.findExamByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("exam", exam); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addExam(Exam exam) {
		// TODO Auto-generated method stub
		examDao.addExam(exam);
	}

	@Override
	public void addFiles(Files files) {
		// TODO Auto-generated method stub
		examDao.addFiles(files);
	}
	
	@Override
	public void addAnswers(Answers answers) {
		// TODO Auto-generated method stub
		examDao.addAnswers(answers);
	}

	@Override
	public void showPaperByUsername(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        ExamPage exampage = null; 
       
        List<Files> files = new ArrayList<Files>(); 
      //查询用户总数
        int totalCount = (int) examDao.getPCByUsername(username);
       
        if (pageNow != null) { 
            exampage = new ExamPage(totalCount, Integer.parseInt(pageNow)); 
            files = this.examDao.findPaperByUsername(username,exampage.getStartPos(),exampage.getPageSize()); 
        } else { 
            exampage = new ExamPage(totalCount, 1); 
            files = this.examDao.findPaperByUsername(username,exampage.getStartPos(),exampage.getPageSize()); 
        } 
       
        model.addAttribute("files", files); 
        model.addAttribute("page", exampage); 
	}

}

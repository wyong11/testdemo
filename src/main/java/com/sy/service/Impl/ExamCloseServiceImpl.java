package com.sy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.ExamCloseDao;
import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.ExamClose;
import com.sy.entity.Page;
import com.sy.entity.Subject;
import com.sy.service.ExamCloseService;

@Service
public class ExamCloseServiceImpl implements ExamCloseService {

	@Autowired
	private ExamCloseDao examCloseDao;
	
	@Override
	public void showECloseByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<ExamClose> examClose = new ArrayList<ExamClose>(); 
      //查询用户总数
        int totalCount = (int) examCloseDao.getECloseCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            examClose = this.examCloseDao.findECloseByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            examClose = this.examCloseDao.findECloseByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("examClose", examClose); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showECloseByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<ExamClose> examClose = new ArrayList<ExamClose>(); 
      //查询用户总数
        int totalCount = (int) examCloseDao.getECloseByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            examClose = this.examCloseDao.findECloseByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            examClose = this.examCloseDao.findECloseByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("examClose", examClose); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addExamClose(ExamClose examClose) {
		// TODO Auto-generated method stub
		examCloseDao.addExamClose(examClose);
	}

	@Override
	public void addAnswers(Answers answers) {
		// TODO Auto-generated method stub
		examCloseDao.addAnswers(answers);
	}

	@Override
	public void showSubjectByEid(int examid, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Subject> subject = new ArrayList<Subject>(); 
      //查询用户总数
        int totalCount = (int) examCloseDao.getSubjectByEid(examid);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            subject = this.examCloseDao.findSubjectByEid(examid,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            subject = this.examCloseDao.findSubjectByEid(examid,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("subject",subject); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addSubject(Subject subject) {
		// TODO Auto-generated method stub
		examCloseDao.addSubject(subject);
	}

}

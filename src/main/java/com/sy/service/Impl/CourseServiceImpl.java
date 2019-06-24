package com.sy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.CourseDao;
import com.sy.dao.GradeDao;
import com.sy.entity.Course;
import com.sy.entity.Grade;
import com.sy.entity.Page;
import com.sy.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;
	
	@Override
	public void showCourseByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Course> course = new ArrayList<Course>(); 
      //查询用户总数
        int totalCount = (int) courseDao.getCourseCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            course = this.courseDao.findCourseByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            course = this.courseDao.findCourseByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("course", course); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showCourseByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Course> course = new ArrayList<Course>(); 
      //查询用户总数
        int totalCount = (int) courseDao.getCourseByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            course = this.courseDao.findCourseByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            course = this.courseDao.findCourseByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("course", course); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showCourseByTname(String teacher, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Course> course = new ArrayList<Course>(); 
      //查询用户总数
        int totalCount = (int) courseDao.getCourseByTname(teacher);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            course = this.courseDao.findCourseByTname(teacher,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            course = this.courseDao.findCourseByTname(teacher,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("course", course); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addCourse(Course course) {
		// TODO Auto-generated method stub
		courseDao.addCourse(course);
	}

}

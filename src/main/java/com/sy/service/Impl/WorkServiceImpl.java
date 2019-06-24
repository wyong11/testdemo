package com.sy.service.Impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.WorkDao;
import com.sy.entity.Files;
import com.sy.entity.Page;
import com.sy.entity.Student;
import com.sy.entity.Work;
import com.sy.entity.WorkStatus;
import com.sy.service.WorkService;

@Service
public class WorkServiceImpl implements WorkService {

	@Autowired
	private WorkDao workDao;
	
	@Override
	public void showWorkByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Work> work = new ArrayList<Work>(); 
      //查询用户总数
        int totalCount = (int) workDao.getWorkCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            work = this.workDao.findWorkByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            work = this.workDao.findWorkByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("work", work); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showWorkByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Work> work = new ArrayList<Work>(); 
      //查询用户总数
        int totalCount = (int) workDao.getWCByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            work = this.workDao.findWorkByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            work = this.workDao.findWorkByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("work", work); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showWSByUsername(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<WorkStatus> workStatus = new ArrayList<WorkStatus>(); 
      //查询用户总数
        int totalCount = (int) workDao.getWSByUsername(username);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            workStatus = this.workDao.findWSByUsername(username,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            workStatus = this.workDao.findWSByUsername(username,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("workStatus", workStatus); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addWork(Work work) {
		// TODO Auto-generated method stub
		workDao.addWork(work);
	}

	@Override
	public void showStudentByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Student> student = new ArrayList<Student>(); 
      //查询用户总数
        int totalCount = (int) workDao.getStudentByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            student = this.workDao.findStudentByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            student = this.workDao.findStudentByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("student", student); 
        model.addAttribute("page", page); 
	}

	@Override
	public void getFilesName(String path, int deep) {
		// TODO Auto-generated method stub
		 // 获得指定文件对象  
        File file = new File(path);   
        // 获得该文件夹内的所有文件   
        File[] array = file.listFiles();   

        for(int i=0;i<array.length;i++)
        {   
            if(array[i].isFile())//如果是文件
            {   
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");
                // 只输出文件名字  
                System.out.println( array[i].getName());   
                // 输出当前文件的完整路径   
               // System.out.println("#####" + array[i]);   
                // 同样输出当前文件的完整路径   大家可以去掉注释 测试一下   
               // System.out.println(array[i].getPath());   
            }
            else if(array[i].isDirectory())//如果是文件夹
            {  
                    for (int j = 0; j < deep; j++)//输出前置空格
                    System.out.print(" ");

                    System.out.println( array[i].getName());
                    //System.out.println(array[i].getPath());
                    //文件夹需要调用递归 ，深度+1
                getFilesName(array[i].getPath(),deep+1);  
            }   
        }   
	}

	@Override
	public void addWorkFiles(Files files) {
		// TODO Auto-generated method stub
		workDao.addWorkFiles(files);
	}

	@Override
	public void showFilesByUsername(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Files> files = new ArrayList<Files>(); 
      //查询用户总数
        int totalCount = (int) workDao.getFilesByUsername(username);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            files = this.workDao.findFilesByUsername(username,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            files = this.workDao.findFilesByUsername(username,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("files", files); 
        model.addAttribute("page", page); 
	}

	@Override
	public Files findFCByUF(String username, String filename) {
		// TODO Auto-generated method stub
		return workDao.findFCByUF(username, filename);
	}

	@Override
	public void showWorkByCC(String classname, String coursename, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Work> work = new ArrayList<Work>(); 
      //查询用户总数
        int totalCount = (int) workDao.getWorkByCC(classname,coursename);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            work = this.workDao.findWorkByCC(classname,coursename,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            work = this.workDao.findWorkByCC(classname,coursename,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("work", work); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addWorkStatus(WorkStatus workStatus) {
		// TODO Auto-generated method stub
		workDao.addWorkStatus(workStatus);
	}

	@Override
	public boolean updateWorkStatus(WorkStatus workStatus) {
		// TODO Auto-generated method stub
		return workDao.updateWorkStatus(workStatus);
	}

	@Override
	public WorkStatus findWSByUW(String username, String workname) {
		// TODO Auto-generated method stub
		return workDao.findWSByUW(username, workname);
	}

	@Override
	public void showWSByCW(String classname, String workname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<WorkStatus> workStatus = new ArrayList<WorkStatus>(); 
      //查询用户总数
        int totalCount = (int) workDao.getWSByCW0(classname,workname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            workStatus = this.workDao.findWSByCW(classname,workname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            workStatus = this.workDao.findWSByCW(classname,workname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("workStatus", workStatus); 
        model.addAttribute("page", page); 
	}

}

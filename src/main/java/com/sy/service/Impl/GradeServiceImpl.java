package com.sy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.GradeDao;
import com.sy.entity.Grade;
import com.sy.entity.Page;
import com.sy.service.GradeService;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeDao gradeDao;
	
	@Override
	public void showGradeByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Grade> grade = new ArrayList<Grade>(); 
      //查询用户总数
        int totalCount = (int) gradeDao.getGradeCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            grade = this.gradeDao.findGradeByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            grade = this.gradeDao.findGradeByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("grade", grade); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showGradeByClassname(String classname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Grade> grade = new ArrayList<Grade>(); 
      //查询用户总数
        int totalCount = (int) gradeDao.getGCByClassname(classname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            grade = this.gradeDao.findGradeByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            grade = this.gradeDao.findGradeByClassname(classname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("grade", grade); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showGradeByUsername(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Grade> grade = new ArrayList<Grade>(); 
      //查询用户总数
        int totalCount = (int) gradeDao.getGCByUsername(username);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            grade = this.gradeDao.findGradeByUsername(username,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            grade = this.gradeDao.findGradeByUsername(username,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("grade", grade); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addGrade(Grade grade) {
		// TODO Auto-generated method stub
		gradeDao.addGrade(grade);
	}

	@Override
	public int getGrade(String Str_1, String Str_2) {
		// TODO Auto-generated method stub
		//length
		int Length1=Str_1.length();
		int Length2=Str_2.length();
		
		int Distance=0;
		if (Length1==0) {
			Distance=Length2;
		}
		if(Length2==0)
		{
			Distance=Length1;
		}
		if(Length1!=0&&Length2!=0){
			int[][] Distance_Matrix=new int[Length1+1][Length2+1];
			//编号
			int Bianhao=0;
			for (int i = 0; i <= Length1; i++) {
					Distance_Matrix[i][0]=Bianhao;
					Bianhao++;
			}
			Bianhao=0;
			for (int i = 0; i <=Length2; i++) {
				Distance_Matrix[0][i]=Bianhao;
				Bianhao++;
			}
			char[] Str_1_CharArray=Str_1.toCharArray();
			char[] Str_2_CharArray=Str_2.toCharArray();
			for (int i = 1; i <= Length1; i++) {
				for(int j=1;j<=Length2;j++){
					if(Str_1_CharArray[i-1]==Str_2_CharArray[j-1]){
						Distance=0;
					}	
					else{
						Distance=1;
					}
						int Temp1=Distance_Matrix[i-1][j]+1;
						int Temp2=Distance_Matrix[i][j-1]+1;
						int Temp3=Distance_Matrix[i-1][j-1]+Distance;
						
						Distance_Matrix[i][j]=Temp1>Temp2?Temp2:Temp1;
						Distance_Matrix[i][j]=Distance_Matrix[i][j]>Temp3?Temp3:Distance_Matrix[i][j];
					
				}
				
			}
			
			Distance=Distance_Matrix[Length1][Length2];
		}
		
		double Aerfa=1-1.0*Distance/(Length1>Length2?Length1:Length2);
		int result = (int) (Aerfa * 100);
		return result;
		
	}

	@Override
	public boolean updateGrade(Grade grade) {
		// TODO Auto-generated method stub
		return gradeDao.updateGrade(grade);
	}

	@Override
	public Grade findGradeByUE(String username,String examname) {
		// TODO Auto-generated method stub
		return gradeDao.findGradeByUE(username,examname);
	}

	@Override
	public void showGradeByCE(String classname, String examname, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Grade> grade = new ArrayList<Grade>(); 
      //查询用户总数
        int totalCount = (int) gradeDao.getGCByCE(classname,examname);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            grade = this.gradeDao.findGradeByCE(classname,examname,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            grade = this.gradeDao.findGradeByCE(classname,examname,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("grade", grade); 
        model.addAttribute("page", page); 
	}

}

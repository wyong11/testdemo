package com.sy.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.sy.dao.SignDao;
import com.sy.dao.UserDao;
import com.sy.entity.Page;
import com.sy.entity.Sign;
import com.sy.entity.SignStatus;
import com.sy.service.SignService;

@Service
public class SignServiceImpl implements SignService {
	
	@Autowired
	private SignDao signDao;

	@Override
	public void showSignByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Sign> sign = new ArrayList<Sign>(); 
      //查询用户总数
        int totalCount = (int) signDao.getSignCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            sign = this.signDao.selectSignByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            sign = this.signDao.selectSignByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("sign", sign); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showSignByClassname(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<Sign> sign = new ArrayList<Sign>(); 
      //查询用户总数
        int totalCount = (int) signDao.getSCByName(username);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            sign = this.signDao.selectSignByClassname(username,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            sign = this.signDao.selectSignByClassname(username,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("sign", sign); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addSign(Sign sign) {
		// TODO Auto-generated method stub
		signDao.addSign(sign);
	}

	@Override
	public Sign findSignById(int id) {
		// TODO Auto-generated method stub
		return signDao.findSignById(id);
	}

	@Override
	public void addSignStatus(SignStatus signStatus) {
		// TODO Auto-generated method stub
		signDao.addSignStatus(signStatus);
	}

	@Override
	public void showSignStatusByPage(HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<SignStatus> signStatus = new ArrayList<SignStatus>(); 
        //查询用户签到总数
        int totalCount = (int) signDao.getSignStatusCount();
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            signStatus = this.signDao.selectSignStatusByPage(page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            signStatus = this.signDao.selectSignStatusByPage(page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("signStatus", signStatus); 
        model.addAttribute("page", page); 
	}

	@Override
	public void showAbsent(int id, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<SignStatus> signStatus = new ArrayList<SignStatus>(); 
      //查询用户总数
        int totalCount = (int) signDao.getSSCById(id);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            signStatus = this.signDao.findAbsent(id,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            signStatus = this.signDao.findAbsent(id,page.getStartPos(), page.getPageSize()); 
        } 
       
        model.addAttribute("signstatus", signStatus); 
        model.addAttribute("page", page); 
	}

	@Override
	public void addSignInitial(int signId,String className, SignStatus signStatus) {
		// TODO Auto-generated method stub
		signDao.addSignInitial(signStatus);
	}

	@Override
	public SignStatus findSSByUsername(String username) {
		// TODO Auto-generated method stub
		return signDao.findSSByUsername(username);
	}

	@Override
	public boolean updateSignStatus(SignStatus signStatus) {
		// TODO Auto-generated method stub
		return signDao.updateSignStatus(signStatus);
	}

	@Override
	public void showSSByUsername(String username, HttpServletRequest request, Model model) {
		// TODO Auto-generated method stub
		String pageNow = request.getParameter("pageNow"); 
        
        Page page = null; 
       
        List<SignStatus> signStatus = new ArrayList<SignStatus>(); 
      //查询用户总数
        int totalCount = (int) signDao.getSSCByUsername(username);
       
        if (pageNow != null) { 
            page = new Page(totalCount, Integer.parseInt(pageNow)); 
            signStatus = this.signDao.selectSSByUsername(username,page.getStartPos(), page.getPageSize()); 
        } else { 
            page = new Page(totalCount, 1); 
            signStatus = this.signDao.selectSSByUsername(username,page.getStartPos(), page.getPageSize()); 
        } 
      
        model.addAttribute("signstatus", signStatus); 
        model.addAttribute("page", page); 
	}

	@Override
	public SignStatus findSSByUS(String username, String signName) {
		// TODO Auto-generated method stub
		return signDao.findSSByUS(username,signName);
	}

}

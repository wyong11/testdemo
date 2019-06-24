package com.sy.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import com.sy.entity.Sign;
import com.sy.entity.SignStatus;

public interface SignService {

	void showSignByPage(HttpServletRequest request,Model model); 
	void showSignStatusByPage(HttpServletRequest request,Model model); 
	void showSignByClassname(String username,HttpServletRequest request,Model model); 
	void showSSByUsername(String username,HttpServletRequest request,Model model);
	void showAbsent(int id,HttpServletRequest request,Model model); 
	void addSign(Sign sign);
	Sign findSignById(int id);
	void addSignStatus(SignStatus signStatus);
	void addSignInitial(int signId,String className,SignStatus signStatus);
	SignStatus findSSByUsername(String username);
	SignStatus findSSByUS(String username,String signName);
	boolean updateSignStatus(SignStatus signStatus);
}

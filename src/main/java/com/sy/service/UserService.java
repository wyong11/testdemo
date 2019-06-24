package com.sy.service;

import java.util.List;
import java.util.Map;

import com.sy.entity.Paper;
import com.sy.entity.PaperContent;
import com.sy.entity.PaperInfo;
import com.sy.entity.Sign;
import com.sy.entity.User;

//Service层接口
public interface UserService {
	
	//检验用户登录
	User checkLogin(String name,String eamil);
	
	void Regist(User user);
	
	public Object insertSalesChannel(List<User> userlist) throws Exception;
	
	public List<PaperInfo> findAllPaper();
	
	//public PaperContent findPaperContent(int pid);
	public List<PaperContent> findPaperContent();
	
	public List<Sign> findSignByClassname();
	//Sign findSignByClassname(String classname);
	
}

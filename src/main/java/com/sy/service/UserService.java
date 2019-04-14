package com.sy.service;

import java.util.List;
import java.util.Map;

import com.sy.entity.User;

//Service层接口
public interface UserService {
	
	//检验用户登录
	User checkLogin(String username,String password);
	
	void Regist(User user);
	
	public Object insertSalesChannel(List<User> userlist) throws Exception;

}

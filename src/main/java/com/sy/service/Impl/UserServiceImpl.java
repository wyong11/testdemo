package com.sy.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.dao.UserDao;
import com.sy.entity.User;
import com.sy.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	/* 
	 * 检验用户登录业务
	 * 
	 */
	public User checkLogin(String username, String password) {
		
		User user = userDao.findByUsername(username);
		if(user != null && user.getPassword().equals(password)){
		
			return user;
		}
		return null;
	}
	
	public Object insertSalesChannel(List<User> userlist) throws Exception {
	// TODO Auto-generated method stub
	return userDao.insertSalesChannel(userlist);
	}

	@Override
	public void Regist(User user) {
		// TODO Auto-generated method stub
		userDao.regist(user.getUsername(),user.getPassword(),user.getUsertype());
	}

	
}

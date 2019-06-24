package com.sy.service.Impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sy.dao.UserDao;
import com.sy.entity.Paper;
import com.sy.entity.PaperContent;
import com.sy.entity.PaperInfo;
import com.sy.entity.Sign;
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
	public User checkLogin(String name, String email) {
		
		User user = userDao.findByUsername(name);
		if(user != null && user.getEmail().equals(email)){
		
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
		userDao.regist(user.getName(),user.getPassword(),user.getType());
	}

	@Override
	public List<PaperInfo> findAllPaper() {
		// TODO Auto-generated method stub
		return userDao.findAllPaper();
	}

	@Override
	public List<PaperContent> findPaperContent() {
		// TODO Auto-generated method stub
		return userDao.findPaperContent();
	}

	@Override
	public List<Sign> findSignByClassname() {
		// TODO Auto-generated method stub
		return userDao.findSignByClassname();
	}

}

package com.sy.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Paper;
import com.sy.entity.PaperContent;
import com.sy.entity.PaperInfo;
import com.sy.entity.Sign;
import com.sy.entity.User;

public interface UserDao {
	
	
	/**
	 * 查找用户名和密码
	 * @param username 登录用户名 
	 * @param password 密码
	 * @return
	 */
	User findByUsername(String name);
	
	int addUser(User user);

	Object insertSalesChannel(List<User> userlist) throws Exception;
	
	/**
	 * 注册用户和密码
	 * @param username 用户名
	 * @param password 密码
	 * @return
	 */
	void regist(@Param("username")String username,@Param("password")String password,@Param("usertype")int usertype);
	
	List<PaperInfo> findAllPaper();
	
	//PaperContent findPaperContent(int pid);
	List<PaperContent> findPaperContent();
	
	List<Sign> findSignByClassname();
	//Sign findSignByClassname(String classname);

}

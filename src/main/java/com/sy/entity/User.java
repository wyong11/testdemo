package com.sy.entity;

import java.util.List;

public class User {
	
	private String username;
	
	private String password;
	
	private int usertype;
	
	public List<User> list;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUsertype() {
		return usertype;
	}

	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}
}

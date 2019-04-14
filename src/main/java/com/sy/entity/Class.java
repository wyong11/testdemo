package com.sy.entity;

import java.util.List;

public class Class {
	
	private String id;
	private String username;
	private String fullname;
	private String acatarurl;
	public List<Class> list;
	public String getAcatarurl() {
		return acatarurl;
	}
	public void setAcatarurl(String acatarurl) {
		this.acatarurl = acatarurl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	private String description;
	private String website;
	private String location;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	

}

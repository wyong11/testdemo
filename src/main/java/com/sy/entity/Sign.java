package com.sy.entity;

public class Sign {

	private int id;
	private String name;
	private String className;
	private String creatTime;
	private String endTime;
	private int stotal;
	private int roomtype;
	
	/*
	 * public Sign() { super(); }
	 * 
	 * public Sign(int id, String name, String className,String creatTime,String
	 * endTime,int time) { super(); this.id = id; this.name = name; this.className =
	 * className; this.creatTime = creatTime; this.endTime = endTime; this.time =
	 * time; }
	 */
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreatTime() {
		return creatTime;
	}
	public void setCreatTime(String creatTime) {
		this.creatTime = creatTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public int getStotal() {
		return stotal;
	}
	public void setStotal(int stotal) {
		this.stotal = stotal;
	}
	public int getRoomtype() {
		return roomtype;
	}
	public void setRoomtype(int roomtype) {
		this.roomtype = roomtype;
	}
	
}

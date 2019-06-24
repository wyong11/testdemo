package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Grade;
import com.sy.entity.SignStatus;

public interface GradeDao {

	public List<Grade> findGradeByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Grade> findGradeByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Grade> findGradeByCE(@Param(value="classname") String classname,@Param(value="examname") String examname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Grade> findGradeByUsername(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public void addGrade(Grade Grade);
	public boolean updateGrade(Grade grade);
	public Grade findGradeByUE(@Param(value="username") String username,@Param(value="examname") String examname);
	
	public long getGradeCount();
	public long getGCByClassname(String classname);
	public long getGCByCE(@Param(value="classname") String classname,@Param(value="examname") String examname);
	public long getGCByUsername(String username);  //通过用户名获取成绩总数
}

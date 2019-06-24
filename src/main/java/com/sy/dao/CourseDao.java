package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Course;
import com.sy.entity.Grade;

public interface CourseDao {
	
	public List<Course> findCourseByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Course> findCourseByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	
	public List<Course> findCourseByTname(@Param(value="teacher") String teacher,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public void addCourse(Course course);
	//public boolean updateGrade(Grade grade);
	
	public long getCourseCount();
	public long getCourseByClassname(String classname);
	public long getCourseByTname(String teacher);  //通过教师名获取课程信息
}

package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.Files;

public interface ExamDao {

	public List<Exam> findExamByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Exam> findExamByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Files> findPaperByUsername(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public void addExam(Exam exam);
	public void addFiles(Files files);
	public void addAnswers(Answers answers);
	
	public long getExamCount();
	public long getECByClassname(String classname);
	public long getPCByUsername(String username);  //通过用户名获取试卷总数
	public long getStotal(@Param(value="examname") String examname,@Param(value="classname") String classname);
	public long getCountGrade(@Param(value="examname") String examname,@Param(value="classname") String classname,@Param(value="examstatus") int examstatus);
	public long getCountGradeU(@Param(value="examname") String examname,@Param(value="classname") String classname,@Param(value="grade") int grade);
}

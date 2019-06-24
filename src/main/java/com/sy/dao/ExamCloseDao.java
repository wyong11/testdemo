package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Answers;
import com.sy.entity.ExamClose;
import com.sy.entity.Subject;

public interface ExamCloseDao {

	public List<ExamClose> findECloseByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<ExamClose> findECloseByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Subject> findSubjectByEid(@Param(value="examid") int examid,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	
	public void addExamClose(ExamClose examClose);
	public void addSubject(Subject subject);
	
	public void addAnswers(Answers answers);
	
	public long getECloseCount();
	public long getECloseByClassname(String classname);
	public long getSubjectByEid(int examid);
	
}

package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Answers;
import com.sy.entity.Exam;
import com.sy.entity.Files;
import com.sy.entity.Grade;
import com.sy.entity.Student;
import com.sy.entity.Work;
import com.sy.entity.WorkStatus;

public interface WorkDao {

	public List<Work> findWorkByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Work> findWorkByCC(@Param(value="classname") String classname,@Param(value="coursename") String coursename,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Work> findWorkByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Student> findStudentByClassname(@Param(value="classname") String classname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<WorkStatus> findWSByUsername(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	public List<WorkStatus> findWSByCW(@Param(value="classname") String classname,@Param(value="workname") String workname,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Files> findFilesByUsername(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public void addWork(Work work);
	public void addWorkStatus(WorkStatus workStatus);
	public void addWorkFiles(Files files);
	public boolean updateWorkStatus(WorkStatus workStatus);
	public WorkStatus findWSByUW(@Param(value="username") String username,@Param(value="workname") String workname);
	
	public Files findFCByUF(@Param(value="username") String username,@Param(value="filename") String filename);
	
	public long getWorkCount();
	public long getWorkByCC(@Param(value="classname") String classname,@Param(value="coursename") String coursename);
	public long getWCByClassname(String classname);
	public long getStudentByClassname(String classname);
	public long getWCByUsername(String username);
	public long getFilesByUsername(String username);
	
	public long getWSByCW0(@Param(value="classname") String classname,@Param(value="workname") String workname);
	public long getWSByCW(@Param(value="classname") String classname,@Param(value="workname") String workname,@Param(value="status") int status);
	public long getWSByUsername(@Param(value="username") String username);

	public Work findWorkById(int testWorkId);

	public WorkStatus updateWorkstatus(String username,int j,int k);
	
}

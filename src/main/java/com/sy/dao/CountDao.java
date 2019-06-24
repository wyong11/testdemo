package com.sy.dao;

import org.apache.ibatis.annotations.Param;

public interface CountDao {

	public long getCountWorkStatus(@Param(value="username") String username,@Param(value="coursename") String coursename,@Param(value="status") int status);	//作业提交次数
	public long getCountWorkStatus2(@Param(value="username") String username,@Param(value="status") int status);	//作业总提交次数

	public long getCountSignStatus(@Param(value="username") String username,@Param(value="coursename") String coursename,@Param(value="status") int status);	//出勤次数
	public long getCountSignStatus2(@Param(value="username") String username,@Param(value="status") int status);	//出勤总次数

	public long getCountExamStatus(@Param(value="username") String username,@Param(value="coursename") String coursename,@Param(value="examstatus") int examstatus);	//参与次数
	public long getCountExamStatus2(@Param(value="username") String username,@Param(value="examstatus") int examstatus);	//参与次数

	public long getCountExamScore(@Param(value="username") String username,@Param(value="coursename") String coursename,@Param(value="grade") int grade);	//成绩及格次数
	public long getCountExamScore2(@Param(value="username") String username,@Param(value="grade") int grade);	//成绩总及格次数

	public long getCountExamStotal(@Param(value="username") String username,@Param(value="coursename") String coursename);	//同课程考试次数
	public long getCountExamStotal2(@Param(value="username") String username);	//学生考试总次数

	
	
	}

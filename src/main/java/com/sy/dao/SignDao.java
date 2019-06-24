package com.sy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sy.entity.Sign;
import com.sy.entity.SignStatus;

public interface SignDao {
	
	public List<Sign> selectSignByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<SignStatus> selectSignStatusByPage(@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<SignStatus> selectSSByUsername(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<Sign> selectSignByClassname(@Param(value="username") String username,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public List<SignStatus> findAbsent(@Param(value="id") Integer id,@Param(value="startPos") Integer startPos,
	        @Param(value="pageSize") Integer pageSize);
	
	public long getSignCount();				//签到总数
	public long getSignStatusCount();				//签到记录数
	public long getSCByName(String name);			//
	public long getSSCById(int id);
	public long getSSCByUsername(String username);
	
	public long getCountSign(@Param(value="id") int id,@Param(value="status") int status);				//出勤人数
	
	public void addSign(Sign sign);
	
	public Sign findSignById(int id);
	
	public void addSignStatus(SignStatus signtatus);
	public void addSignInitial(SignStatus signtatus);
	
	public boolean updateSignStatus(SignStatus signtatus);
	public SignStatus findSSByUsername(String username);
	public SignStatus findSSByUS(@Param(value="username") String username,@Param(value="signName") String signName);

}

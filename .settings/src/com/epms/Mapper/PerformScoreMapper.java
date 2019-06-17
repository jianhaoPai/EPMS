package com.epms.Mapper;

import java.util.List;















import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.PerformScore;
import com.epms.Bean.User;

@Repository
public interface PerformScoreMapper {
	
	//查询未评价的记录
	public List<PerformScore> showAllEvaluate(@Param("jobId")int jobId,@Param("before")int before,@Param("after")int after);
	public int count(@Param("jobId")int jobId);
	
	//添加绩效评价
	public int addPerform(int jobId,int evaluateId,int score, String date, int as1, int as2, int as3,
			int as4, int as5, int as6, int as7, int as8, int as9, int as10,
			int as11, int as12, int as13, int as14, int as15, int as16,
			int as17, int as18, int as19, int as20);
	
	//查询被评价人是否为总经理
	public String checkGeneralManager(@Param("evaluateId")int evaluateId);
	//查询经理数量
	public int checkManagerNum();
	//查询评价数量
	public int checkPerformNum(@Param("evaluateId")int evaluateId,@Param("assessDate") String assessDate);
	//计算绩效总分
	public int countPerformScore(@Param("evaluateId")int evaluateId,@Param("assessDate") String assessDate);
	//查询被评价人是否为部门经理
	public int checkManager(@Param("evaluateId")int evaluateId);
	//部门内的人数
	public int checkPeopleNum(@Param("jobId")int evaluateId);
	//查询被评价人是否为普通职员
	public int checkStaff(@Param("evaluateId")int evaluateId);
	//修改评价状态
	public int updatePerformScoreState(@Param("jobId")int jobId,@Param("assessDate")String assessDate);
	//初始化总经理绩效评价
	public int initGeneralManagerEvaluate(@Param("jobId")int jobId,@Param("managerList")List<User> managerList,@Param("nowTime")String nowTime);
	//初始化部门经理对总经理的绩效评价
	public int addMToGMEvaluate(@Param("managerList")List<User> managerList,@Param("GMjobId") int GMjobId,@Param("nowTime")String nowTime);
	//初始化部门经理对部门内员工的绩效评价
	public int addMToStaffEvaluate(@Param("MjobId")String MjobId,@Param("staffList") List<User> staffList,@Param("nowTime")String nowTime);
	//初始化部门内员工对部门经理的绩效评价
	public int addStaffToMEvaluate(@Param("staffList")List<User> staffList,@Param("MjobId") String MjobId,@Param("nowTime")String nowTime);
	//初始化部门内员工对员工的绩效评价
	public int addStaffToStaff(@Param("SjobId")String SjobId,@Param("colleagueList") List<User> colleagueList,@Param("nowTime")String nowTime);
}

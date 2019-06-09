package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.PerformSum;

@Repository
public interface PerformSumMapper {
	
	/*
	 * 查询本人绩效评价的记录
	 */
	public List<PerformSum> showAllPerform(int jobId,int before,int after);
	//计算总条数
	public int count(@Param("jobId")int jobId);
	//添加总绩效成绩
	public int addPerformSum(@Param("evaluateId")int evaluateId,@Param("score") double score,@Param("type") String type,@Param("assessDate") String assessDate);
	//审核绩效评价记录
	public int updateVerifyPerform(@Param("jobId")int jobId,@Param("nowTime")String nowTime,@Param("assessDate")String assessDate,@Param("approverId")int approverId);
	//显示经理的部门中需要审核的绩效评价记录
	public List<PerformSum> showVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取经理的部门中需要审核的绩效评价记录条数
	public int countStaffVerifyPerform(int jobId);
	//获取总经理需要审核的绩效评价记录
	public List<PerformSum> showGeneralManagerVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取总经理中需要审核的绩效评价记录条数
	public int countManagerVerifyPerform(int jobId);
	//判断审核人的身份(总经理/部门经理/董事)
	public String findIdentityByJobId(int jobId);
	//获取董事需要审核的绩效评价记录
	public List<PerformSum> showDirectorVerifyPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取董事需要审核的绩效评价记录条数
	public int countDirectorVerifyPerform(int jobId);
	//获取部门经理/总经理查看员工的绩效评价记录
	public List<PerformSum> showStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取部门经理/总经理查看员工的绩效评价记录条数
	public int countStaffPerform(int jobId);
	//获取董事查询员工绩效评价记录
	public List<PerformSum> showDirectorStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取董事查询员工绩效评价记录条数
	public int countDirectorStaffPerform(int jobId);
	//获取总经理查询员工绩效评价记录
	public List<PerformSum> showGeneralManagerStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取总经理查询员工绩效评价记录条数
	public int countGeneralManagerStaffPerform(int jobId);
	//获取部门经理查询员工绩效评价记录
	public List<PerformSum> showManagerStaffPerform(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);
	//获取部门经理查询员工绩效评价记录条数
	public int countManagerStaffPerform(int jobId);
	
	//删除总绩效评价的记录
	public int deletePerformSum(@Param("jobId")int jobId,@Param("assessDate") String assessDate);

}

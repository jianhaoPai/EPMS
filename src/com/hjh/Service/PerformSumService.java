package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.PerformSum;

public interface PerformSumService {
	
	//查询本人的绩效评价记录
	public List<PerformSum> showAllPerform(int jobId,int before,int after);
	//计算记录的总条数
	public int count(int jobId);
	//显示经理的部门中需要审核的绩效评价记录
	public List<PerformSum> showVerifyPerform(int jobId, int before, int after);
	//获取经理的部门中需要审核的绩效评价记录条数
	public int countStaffVerifyPerform(int jobId);
	//审核绩效评价记录
	public int updateVerifyPerform(int jobId, String assessDate, int approverId);
	//获取总经理需要审核的绩效评价记录
	public List<PerformSum> showGeneralManagerVerifyPerform(int jobId,int before, int after);
	//获取总经理中需要审核的绩效评价记录条数
	public int countManagerVerifyPerform(int jobId);
	//通过工号获取身份（总经理，部门经理，董事）
	public String findIdentityByJobId(int jobId);
	//获取董事需要审核的绩效评价记录
	public List<PerformSum> showDirectorVerifyPerform(int jobId,int before, int after);
	//获取董事需要审核的绩效评价记录条数
	public int countDirectorVerifyPerform(int jobId);
	//绩效评价不通过
	public int updateVerifyPerformNo(int jobId, String assessDate);
	//获取董事查询员工绩效评价记录
	public List<PerformSum> showDirectorStaffPerform(int jobId, int before,int after);
	//获取董事查询员工绩效评价记录条数
	public int countDirectorStaffPerform(int jobId);
	//获取总经理查询员工绩效评价记录
	public List<PerformSum> showGeneralManagerStaffPerform(int jobId,int before, int after);
	//获取总经理查询员工绩效评价记录条数
	public int countGeneralManagerStaffPerform(int jobId);
	//获取部门经理查询员工绩效评价记录
	public List<PerformSum> showManagerStaffPerform(int jobId, int before,int after);
	//获取部门经理查询员工绩效评价记录条数
	public int countManagerStaffPerform(int jobId);
	
	

}

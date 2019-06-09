package com.hjh.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hjh.Bean.SalaryAdjust;


public interface SalaryAdjustService 
{
	//提交薪资调动表
	public String insertSalaryAdjust(SalaryAdjust salaryAdjust,String passiveName);
	
	//根据填表人id查询薪资调动申请信息
	List<SalaryAdjust> selectAllSalaryAdjustByWriteId(String department_id,String state,
			                        String salaryadjust_type,int before,int after,int jobId);
	int countByWriteId(String department_id,String state,String salaryadjust_type,int jobId);

	//上级查看下属提交的全部薪资调动申请
	public List<SalaryAdjust> selectAllSalaryAdjust(String department_id,String state,String salaryadjust_type,int before,int after,int jobId);	
	public int countAllSalaryAdjust (String department_id,String state,String salaryadjust_type,int jobId);
	
	//审批申请事项信息
  	String updateSalaryAdjust(SalaryAdjust salaryAdjust,String state);
  	
    //根据工号查询相关的薪资调动记录
  	List<SalaryAdjust> selectAllSalaryAdjustByJobId(String state,int before,int after,int jobId);
  	int countByJobId(String state,int jobId);

}




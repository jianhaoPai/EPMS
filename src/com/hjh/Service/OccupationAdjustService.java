package com.hjh.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hjh.Bean.OccupationAdjust;
public interface OccupationAdjustService 
{
	//提交薪资调动表
	public String insertOccupationAdjust(OccupationAdjust occupationAdjust,String passiveName);
	
	//根据填表人id查询薪资调动申请信息
	List<OccupationAdjust> selectAllOccupationAdjustByWriteId(String pre_departmentid,String state,
			               String type,int before,int after,int jobId);
	int countByWriteId(String pre_departmentid,String state,String type,int jobId);
	
	//上级查看下属提交的全部职位调动申请
	public List<OccupationAdjust> selectAllSalaryAdjust(String pre_departmentid,String state,String type,int before,int after,int jobId);	
	public int countAllOccupationAdjust (String pre_departmentid,String state,String type,int jobId);
	
	//审批申请事项信息
  	String updateOccupationAdjust(OccupationAdjust occupationAdjust,String state);
  	
	//根据工号查询相关的职位调动记录
	List<OccupationAdjust> selectAllOccupationAdjustByJobId(String state,String type,int before, int after,int jobId);
	int countByJobId(String state,String type,int jobId);

}




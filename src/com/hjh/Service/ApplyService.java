package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Apply;


public interface ApplyService 
{
	//部门经理提交事项申请表
	public String insertApply(Apply apply,String passiveName);

	//查看全部申请事项信息
	public List<Apply> selectAllApply(String department_id,String state,String applytype_name,
			                          int before,int after,int jobId);	
    public int count (String department_id,String state,String applytype_name,int jobId);
    
    //根据填表人id查询申请事项信息
  	List<Apply> selectAllApplyByWriteId(String department_id,String state,String applytype_name,
  			                            int before,int after,int jobId);
  	int countByWriteId(String department_id,String state,String applytype_name,int jobId);
    
    //审批申请事项信息
  	String updateApply(Apply apply);
}

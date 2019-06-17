package com.epms.Service;

import java.util.List;

import com.epms.Bean.InteralResume;
//内部人员简历表
public interface InteralResumeService 
{
	//内部人员填写简历
	public String insertInteralResume(InteralResume interalResume);
	
	//通过工号查询提交的简历信息
    public List<InteralResume> selectInteralResumeByJobId(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countByJobId(String departmentId,String occupationId,String status,int jobId);
	
	//总经理或部门经理查询简历信息
	public List<InteralResume> selectAllInteralResume(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countAllInteralResume(String departmentId,String occupationId,String status,int jobId);
}

package com.epms.Service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.epms.Bean.ExternalResume;
//外部人员简历表
public interface ExternalResumeService 
{
	//提交简历
	public String insertExternalResume(ExternalResume externalResume);
	
	//查询全部外部人员简历
	public List<ExternalResume> selectAllExternalResume(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countSelectAllExternalResume(String departmentId,String occupationId,String status,int jobId);	

	//邮箱获取
	public ExternalResume getExternalResumeByEmail(String email);



}

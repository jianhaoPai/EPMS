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
	public List<ExternalResume> selectAllExternalResume(@Param("before") int before,@Param("after") int after,@Param("jobId") int jobId);
	public int countSelectAllExternalResume(int jobId);	
	
	/*//通过名字查询出所有的简历
	public List<ExternalResume> selectExternalResumeByName(String name);

	public 	List<ExternalResume> selectExternalResumeAll(int before, int after);
	//计算总条数
	public int count();*/





}

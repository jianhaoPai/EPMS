package com.epms.Service;

import java.util.List;

import com.epms.Bean.Resume;

//简历表
public interface ResumeService 
{
	//通过工号查询提交的简历信息
    public List<Object> selectAllResumeByJobId(String departmentId,String occupationId,String status,int before,int after,int jobId);
	public int countByJobId(String departmentId,String occupationId,String status,int jobId);
	//审核简历
	public String updateAllResume(Resume resume,String interviewName);
	
	
}

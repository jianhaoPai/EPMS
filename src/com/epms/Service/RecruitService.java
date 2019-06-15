package com.epms.Service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.epms.Bean.Recruit;

//招聘计划发布
public interface RecruitService {
	//提交招聘计划
	public String insertRecruit(Recruit recruit);
	
	//上级查询下级提交的全部招聘信息
	public List<Recruit> selectAllRecruit(String occupationId,String departmentId,String status,int before,int after,int jobId);
	public int countAllRecruit(String occupationId,String departmentId,String status,int jobId);
	
	//审核招聘计划
	public String updateRecruitStatus(Recruit recruit);
	
	//查询自己提交的招聘计划
	List<Recruit> selectAllRecruitByWriteId(int before,int after,int writeId);
	int countSelectAllRecruitByWriteId(int writeId);
	
	//员工查询招聘计划
	List<Recruit> selectAllRecruitToEmployee(String occupationId,String departmentId,int before,int after);
	int countSelectAllRecruitToEmployee(String occupationId,String departmentId);
	


	
}

package com.epms.Mapper;

import org.springframework.stereotype.Repository;

import com.epms.Bean.StaffWage;

@Repository
public interface StaffWageMapper 
{
	//修改工资
	int updatetStaffWage(StaffWage staffWage);
	
	//根据工号查询工资
	StaffWage selectStaffWageByJobId(int jobId);
}

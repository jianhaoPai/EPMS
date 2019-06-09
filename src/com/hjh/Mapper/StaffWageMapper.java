package com.hjh.Mapper;

import org.springframework.stereotype.Repository;
import com.hjh.Bean.StaffWage;

@Repository
public interface StaffWageMapper 
{
	//修改工资
	int updatetStaffWage(StaffWage staffWage);
	
	//根据工号查询工资
	StaffWage selectStaffWageByJobId(int jobId);
}

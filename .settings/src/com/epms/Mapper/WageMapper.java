package com.epms.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.epms.Bean.StaffWage;
import com.epms.Bean.Wage;
import com.epms.Bean.WelfareUser;
@Repository
public interface WageMapper {

	//查看本人工资
	public List<Wage> showWageByJobId(@Param("jobId")int jobId,@Param("before") int before,@Param("after") int after);

	//查看个人工资条数
	public int countByJobId(int jobId);

	//添加工资记录
	public void addWage(@Param("jobId")int jobId,@Param("wageTime") String wageTime,@Param("basicWage") double basicWage,
			@Param("overTimePay")double overTimePay,@Param("liveAllowance") double liveAllowance,@Param("holiday") double holidayAllowance,@Param("performAllowance") double performAllowance,@Param("absence") double absence,
			@Param("socialSecurity")double socialSecurity,@Param("hosingFund") double housingFund,@Param("borrow") double borrow,@Param("other") double other,
			@Param("wageTax")double wageTax,@Param("taxAmount") double taxAmount,@Param("sum") double sum);

	//查询基本工资
	public StaffWage selectWageByJobId(int jobId);
	//查询工资记录
	public Wage selectWage(int jobId,String wageTime);

	//查看需要工资审核的记录
	public List<Wage> showVerifyWage(@Param("before")int before,@Param("limit") int limit);

	//查看需要工资审核的记录条数
	public int countVerifyWage();

}

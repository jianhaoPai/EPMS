package com.epms.Service;

import java.util.List;

import com.epms.Bean.TotalData;
import com.epms.Bean.Wage;

public interface WageService {

	//查看本人工资
	List<Wage> showWageByJobId(int jobId, int before, int after);

	//查看个人工资条数
	int countByJobId(int jobId);

	//计算工资
	void countStaffWage(List<TotalData> totalData);

	//查看需要工资审核的记录
	List<Wage> showVerifyWage(int before, int limit);

	//查看需要工资审核的记录条数
	int countVerifyWage();

}

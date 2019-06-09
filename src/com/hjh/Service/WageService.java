package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Wage;
import com.hjh.Bean.WelfareUser;

public interface WageService {

	//查看本人工资
	List<Wage> showWageByJobId(int jobId, int before, int after);

	//查看个人工资条数
	int countByJobId(int jobId);

}

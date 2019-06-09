package com.hjh.Service;

import java.util.List;
import com.hjh.Bean.VacationRule;


public interface VacationRuleService 
{		
	//修改假期规定天数
	String updatetVacationRule(VacationRule vacationRule);
		
	//查询假期规定天数
	List<VacationRule> selectVacationRule(int before,int after);
	int count();
	
	
}

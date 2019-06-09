package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.VacationRule;

@Repository
public interface VacationRuleMapper 
{
	//修改假期规定天数
	int updatetVacationRule(VacationRule vacationRule);
	
	//查询假期规定天数
    List<VacationRule> selectVacationRule(@Param("before") int before,@Param("after") int after);
    int count();
	
}

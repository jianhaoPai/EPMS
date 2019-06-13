package com.epms.Service;

import org.apache.ibatis.annotations.Param;


public interface WorkingCalendarService 
{		
	// 根据输入的月份查询工作天数
	int CountWorkSum(String year,String month);

	// 根据输入的月份查询节假日天数
	int CountNotWorkSum(String year,String month);
	
	
}

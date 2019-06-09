package com.hjh.Service;

import com.hjh.Bean.AttendanceRule;


public interface AttendanceRuleService 
{
	//修改上下班时间
	String updatetAttendanceRuleAll(AttendanceRule attendanceRule);
		
	//查询上下班时间
	AttendanceRule selectAttendanceRule();
	
}

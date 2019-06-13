package com.epms.Service;

import com.epms.Bean.AttendanceRule;


public interface AttendanceRuleService 
{
	//修改上下班时间
	String updatetAttendanceRuleAll(AttendanceRule attendanceRule);
		
	//查询上下班时间
	AttendanceRule selectAttendanceRule();
	
}

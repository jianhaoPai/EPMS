package com.hjh.Mapper;

import org.springframework.stereotype.Repository;

import com.hjh.Bean.AttendanceRule;

@Repository
public interface AttendanceRuleMapper 
{
	//修改上班时间
	int updatetAttendanceRuleStart(AttendanceRule attendanceRule);
	
	//修改下班时间
	int updatetAttendanceRuleFinish(AttendanceRule attendanceRule);
	
	//修改上下班时间
	int updatetAttendanceRuleAll(AttendanceRule attendanceRule);
	
	//查询上下班时间
	AttendanceRule selectAttendanceRule();
	
}

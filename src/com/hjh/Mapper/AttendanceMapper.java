package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.WorkingCalendar;

@Repository
public interface AttendanceMapper 
{
	 //上班打卡
	 int insertInAttendance(Attendance attendance);
	 
	 //下班打卡
	 int insertOutAttendance(Attendance attendance);
	 
	 //检查是否已有打卡
	 Attendance SelectAttendanceByTodayAndJobId(Attendance attendance);	
	 
	 //通过id查询全部打卡记录
	 List<Attendance> selectAllById (@Param("before") int before,@Param("after") int after,@Param("jobId")int jobId,@Param("today")String today);
	 int count (@Param("jobId")int jobId,@Param("today")String today);
	 
	 /*//根据日期模糊查询每个员工的迟到、早退、加班时长
	 List<Attendance> selectSumDataByDate(@Param("before") int before,@Param("after") int after,
			                              @Param("startDate")String startDate);
	 int countSumDataByDate (@Param("startDate")String startDate);*/
	 
	 //查询某月每个员工的加班(以半小时为单位)时长
	 List<Attendance> selectSumOvertimeByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //查询某月每个员工的迟到(次数)
	 List<Attendance> selectSumLatelyByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //查询某月每个员工的早退(次数)
	 List<Attendance> selectSumEarlyLeaveByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 //查询某月每个员工的工作日打卡次数
	 List<Attendance> countSumWorkDayByInDate(@Param("workingCalendars")String[] workingCalendars);
	 
	 
	 
	 
	 
}

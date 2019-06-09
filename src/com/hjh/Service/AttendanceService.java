package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.Attendance;
import com.hjh.Bean.TotalData;


public interface AttendanceService 
{
	//上班打卡
	String insertInAttendance(int jobId);
	
	//下班打卡
	String insertOutAttendance(int jobId);
	
	//检查是否已有打卡
	Attendance ChackDate(Attendance attendance);
	
	//通过id查询全部打卡记录
	List<Attendance> selectAllById(int before,int after,int jobId,String today);	
    int count (int jobId,String today);
    
    /*//根据日期模糊查询每个员工的迟到、早退、加班时长
    List<Attendance> selectSumDataByDate(int before,int after,String startDate);	
    int countSumDataByDate(String startDate);*/
    
    //查询某个月每个员工的迟到(次数)、早退(次数)、加班(以半小时为单位)时长。
    List<TotalData> selectAllSumDataByInDate(String year,String month);
	
    //查询某个月每个员工缺勤次数。
    List<TotalData> selectAllSumAbsenceByInDate(String year,String month);
}

package com.epms.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.Attendance;
import com.epms.Bean.AttendanceRule;
import com.epms.Bean.TotalData;
import com.epms.Bean.WorkingCalendar;
import com.epms.Conventer.DateConverter;
import com.epms.Mapper.AttendanceMapper;
import com.epms.Mapper.AttendanceRuleMapper;
import com.epms.Mapper.TotalDataMapper;
import com.epms.Mapper.WorkingCalendarMapper;
import com.epms.Service.AttendanceService;

@Service("attendanceService")
public class AttendanceServiceImpl implements AttendanceService
{
	@Autowired
	private AttendanceMapper attendanceMapper;
	
	@Autowired
	private AttendanceRuleMapper attendanceRuleMapper;
	
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	@Autowired
	private TotalDataMapper totalDataMapper;
	
	@Autowired
	private Attendance attendance;
	
	@Autowired
	private AttendanceRule attendanceRule;
	
	@Autowired
	private TotalData totalData;
	
	//上班打卡
	public String insertInAttendance(int jobId) 
	{
		attendance.setJobId(jobId);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		attendance.setToday(dateFormat2.format(date));
		if(ChackDate(attendance)==null)//检查是否已有打卡记录
		{
			attendance.setStartDate(dateFormat.format(date).toString());
			int nowTime=date.getHours()*60+date.getMinutes();//现在时间换算成分钟
			attendance.setStartTime(nowTime);
			attendanceRule=attendanceRuleMapper.selectAttendanceRule();
			if(checkIfWorkingDay(attendance.getToday()))//判断是否为工作日
			{
				//设置上班迟到时间
				if(nowTime>Integer.parseInt(attendanceRule.getSetStart())*60)
				{
					attendance.setLately(nowTime-Integer.parseInt(attendanceRule.getSetStart())*60);
				} else{
					attendance.setLately(0);}
			}
			else//节假日，无迟到
			{
				attendance.setLately(0);
			}
			//执行上班打卡
			if(attendanceMapper.insertInAttendance(attendance)>0)
			{
				return "上班打卡成功！";
			} else{
				return "上班打卡失败！";
			}
		} else{
			return "请勿重复打卡！";
		}
	}
	
	//下班打卡
	@Override
	public String insertOutAttendance(int jobId) 
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd ");
		attendance.setJobId(jobId);
		Date date=new Date();
		attendance.setToday(dateFormat2.format(date));
		if(ChackDate(attendance)!=null && ChackDate(attendance).getFinishDate()==null)
		{
			attendance.setJobId(jobId);
			attendance.setFinishDate(dateFormat.format(date).toString());
			attendanceRule=attendanceRuleMapper.selectAttendanceRule();
			
			int nowTime=date.getHours()*60+date.getMinutes();
			if(checkIfWorkingDay(attendance.getToday()))//判断是否为工作日
			{
				//设置下班加班时间和设置上班早退时间
				if(nowTime>Integer.parseInt(attendanceRule.getSetFinish())*60)
				{
					attendance.setOverTime(nowTime-Integer.parseInt(attendanceRule.getSetFinish())*60);
					attendance.setEarlyLeave(0);
				} else{
					attendance.setOverTime(0);
					attendance.setEarlyLeave(Integer.parseInt(attendanceRule.getSetFinish())*60-nowTime);
					}
			}
			else//节假日，无早退
			{
				attendance.setEarlyLeave(0);
				//获得此人的上班时间
				Attendance attendance1=attendanceMapper.SelectAttendanceByTodayAndJobId(attendance);
				//计算加班时长
				attendance.setOverTime(nowTime-attendance1.getStartTime());
			}
			//执行下班打卡
			if(attendanceMapper.insertOutAttendance(attendance)>0)
			{
				return "下班打卡成功！";
			}else {
				return "下班打卡失败";
			}
		}else {
			return "请先进行上班打卡或请勿重新打卡！";
		}
	}
	
	//检查是否已有打卡记录
	@Override
	public Attendance ChackDate(Attendance attendance) 
	{
		return attendanceMapper.SelectAttendanceByTodayAndJobId(attendance);
	}
	
	//判断是否为工作日(若为工作日，就需判断是否早退、迟到，节假日打卡只算加班时间)
	public  boolean checkIfWorkingDay(String today)
	{
		if(workingCalendarMapper.checkIfWorkingDay(today)==0)//若为0则为节假日
		{
			return false;
		}
		else//若为1则为工作日，需计算迟到、早退
		{
			return true;
		}
	}
	
	
	//通过工号查询全部打卡记录
	@Override
	public List<Attendance> selectAllById(int before, int after, int jobId,String today) 
	{
		return attendanceMapper.selectAllById(before, after, jobId,today);
	}
	
	@Override
	public int count(int jobId,String today) {
		return attendanceMapper.count(jobId, today);
	}
	
	//根据日期模糊查询每个员工的迟到、早退、加班时长
	/*@Override
	public List<Attendance> selectSumDataByDate(int before,int after,String startDate) 
	{
		return attendanceMapper.selectSumDataByDate(before, after, startDate);
	}

	@Override
	public int countSumDataByDate(String startDate) 
	{
		return attendanceMapper.countSumDataByDate(startDate);
	}
	*/
	
	//查询当月每个员工的迟到(次数)、早退(次数)、加班(以半小时为单位)时长
	@Override
	public List<TotalData> selectAllSumDataByInDate(String year,String month) 
	{	
		//1、算出工作日的日期
		String[] workingList=workingCalendarMapper.selectWorkDate(year, month);
		//算出对应工作日的日期中的总的迟到、早退、加班时长
		List<Attendance> earlyLeaveList=attendanceMapper.selectSumEarlyLeaveByInDate(workingList);
		List<Attendance> lateltList=attendanceMapper.selectSumLatelyByInDate(workingList);
		List<Attendance> overTimeList=attendanceMapper.selectSumOvertimeByInDate(workingList);
		detailWorkingDataByInDate(earlyLeaveList,lateltList,overTimeList);
		
		// 2、算出节假日的日期
		String[] notWorkingList = workingCalendarMapper.selectNotWorkDate(year, month);
		// 算出对应节假日的日期中的加班时长(由于节假日没有迟到、早退，所以只算加班)
		overTimeList=attendanceMapper.selectSumOvertimeByInDate(notWorkingList);
		detailNotWorkingDataByInDate(overTimeList);
		return totalDataMapper.selectAllDataSum();
	}
	
	//工作日的具体计算
	public void detailWorkingDataByInDate(List<Attendance> earlyLeaveList,List<Attendance> lateltList,List<Attendance> overTimeList)
	{
		if (!earlyLeaveList.isEmpty())
		{
			for (int i = 0; i < earlyLeaveList.size(); i++) 
			{
				// 获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
				totalData = totalDataMapper.selectByJobId(earlyLeaveList.get(i).getJobId());
				// 判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByEarlyLeave(earlyLeaveList.get(i).getJobId(),
							earlyLeaveList.get(i).getEarlyLeave());
				} else {
					totalDataMapper.updateTotalDataByEarlyLeave(earlyLeaveList.get(i).getJobId(),
							totalData.getTotalEarlyleave()+ earlyLeaveList.get(i).getEarlyLeave());
				}
			}
		}
		if (!lateltList.isEmpty())
		{
			for (int i = 0; i < lateltList.size(); i++) 
			{
				// 获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
				totalData = totalDataMapper.selectByJobId(lateltList.get(i).getJobId());
				// 判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByLately(lateltList.get(i).getJobId(),lateltList.get(i).getLately());
				} else {
					totalDataMapper.updateTotalDataByLately(lateltList.get(i).getJobId(),
							totalData.getTotalLately()+lateltList.get(i).getLately());
				}
			}
		}
		if (!overTimeList.isEmpty())
		{
			for (int i = 0; i < overTimeList.size(); i++) 
			{
				// 获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
				totalData = totalDataMapper.selectByJobId(overTimeList.get(i).getJobId());
				// 判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByWorkOverTime(overTimeList.get(i).getJobId(),overTimeList.get(i).getOverTime()/30);
				} else {
					totalDataMapper.updateTotalDataByWorkOverTime(overTimeList.get(i).getJobId(),
							totalData.getTotalWorkOverTime()+overTimeList.get(i).getOverTime()/30);
				}
			}
		}
	}
	
	//节假日的具体计算
	public void detailNotWorkingDataByInDate(List<Attendance> overTimeList)
	{
		if (!overTimeList.isEmpty())
		{
			for (int i = 0; i < overTimeList.size(); i++) 
			{
				// 获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
				totalData = totalDataMapper.selectByJobId(overTimeList.get(i).getJobId());
				// 判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
				if (totalData == null) 
				{
					totalDataMapper.insertTotalDataByNotWorkOverTime(overTimeList.get(i).getJobId(),overTimeList.get(i).getOverTime()/30);
				} else {
					totalDataMapper.updateTotalDataByNotWorkOverTime(overTimeList.get(i).getJobId(),
							totalData.getTotalNotWorkOverTime()+overTimeList.get(i).getOverTime()/30);
				}
			}
		}
	}
	
	//查询某个月每个员工缺勤次数。
	@Override
	public List<TotalData> selectAllSumAbsenceByInDate(String year, String month) 
	{
		//计算出某月的工作日天数
		int workDay=workingCalendarMapper.CountWorkSum(year,month);
		
		//算出工作日的日期
		String[] workingList=workingCalendarMapper.selectWorkDate(year, month);
		
		//算出每个员工真正工作日打卡次数,缺勤数等于工作日天数减去打卡次数
		List<Attendance> workSumList=attendanceMapper.countSumWorkDayByInDate(workingList);
		
		for(int i = 0; i < workSumList.size(); i++) 
		{
			// 获得此项申请的结束时间，用其与第一天的期间查询之中有几个工作日，为其的请假天数
			totalData = totalDataMapper.selectByJobId(workSumList.get(i).getJobId());
			// 判断在totalData表中是否已存有数据，若无则使用insert语句，所有则使用update语句
			if (totalData == null) 
			{
				totalDataMapper.insertTotalDataByAbsence(workSumList.get(i).getJobId(),
						workDay-Integer.parseInt(workSumList.get(i).getToday()));
			} else {
				totalDataMapper.updateTotalDataByAbsence(workSumList.get(i).getJobId(),
						totalData.getTotalAbsence()+workDay-Integer.parseInt(workSumList.get(i).getToday()));
			}
		}
		
		//用上一步算出的缺勤数减去请假天数(以在调动此方法前，已计算出请假天数存在数据库中调用即可)，得出进一步的缺勤数
		List<TotalData> totalDateList=totalDataMapper.selectAllDataSum();
		for(int i = 0; i < totalDateList.size(); i++) 
		{
			totalDataMapper.updateTotalDataByAbsence(totalDateList.get(i).getJobId(),
					totalDateList.get(i).getTotalAbsence()-totalDateList.get(i).getTotalVacate());
		}
		
		return totalDataMapper.selectAllDataSum();
	}
	
}

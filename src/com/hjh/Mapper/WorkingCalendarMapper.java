package com.hjh.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.hjh.Bean.WorkingCalendar;

@Repository
public interface WorkingCalendarMapper 
{
	//根据输入的月份查询工作天数
	int CountWorkSum(@Param("year") String year,@Param("month") String month);
 
	//根据输入的月份查询节假日天数
	int CountNotWorkSum(@Param("year") String year,@Param("month") String month);
	
	//输入日期来判断是否为工作日
	int checkIfWorkingDay(@Param("today") String today);
	
	//查询某月的最后一天日期
	String selectMonthLastDate(@Param("year") String year,@Param("month") String month);
	
	//查询某月的第一天日期
	String selectMonthFirstDate(@Param("year") String year,@Param("month") String month);
	
	//根据日期查询id
	int selectIdByDate(@Param("date") String date);
 
	//查询某一区间的节假日天数
	int selectNotWorkBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
	
	//查询某一区间的工作天数
    int selectWorkBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
    
    //根据某一区间计算出其中的每一个日期对应的id
    List<WorkingCalendar> selectIdBetweenDate(@Param("startId") int startId,@Param("finishId") int finishId);
    
    //查询某一月份的全部工作日期
    String[] selectWorkDate(@Param("year") String year,@Param("month") String month);  
    
    //查询某一月份的全部休息日期
    String[] selectNotWorkDate(@Param("year") String year,@Param("month") String month); 
    
	
}

package com.hjh.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Mapper.WorkingCalendarMapper;
import com.hjh.Service.WorkingCalendarService;

@Service("workingCalendarService")
public class WorkingCalendarServiceImpl implements WorkingCalendarService
{
	@Autowired
	private WorkingCalendarMapper workingCalendarMapper;
	
	//根据月份查询工作天数
	@Override
	public int CountWorkSum(String year,String month) 
	{
		return workingCalendarMapper.CountWorkSum(year,month);
	}
	
	//根据月份查询节假日天数
	@Override
	public int CountNotWorkSum(String year,String month)
	{
		return workingCalendarMapper.CountNotWorkSum(year,month);
	}

}

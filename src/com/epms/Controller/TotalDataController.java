package com.epms.Controller;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epms.Service.TotalDataService;

@Controller
@RequestMapping(value="TotalDataController")
public class TotalDataController 
{
	@Autowired
	private TotalDataService totalDataService;
	
	
	//根据查询所有员工当月的迟到，早退，请假，加班，缺勤数据
	@RequestMapping(value="/selectMonthTotalData")
	public void selectMonthTotalData()
	{	
		//还有缺勤没有算
		Calendar a=Calendar.getInstance();
		String year=String.valueOf(a.get(Calendar.YEAR));
		String month=String.valueOf(a.get(Calendar.MONTH)+1);
		System.out.println(totalDataService.selectMonthTotalData(year,month));
	}
	
	
	
	
}

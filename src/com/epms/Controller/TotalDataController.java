package com.epms.Controller;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.TotalData;
import com.epms.Service.TotalDataService;

@Controller
@RequestMapping(value="TotalDataController")
public class TotalDataController 
{
	@Autowired
	private TotalDataService totalDataService;
	
	
	//根据查询所有员工当月的迟到，早退，请假，加班，缺勤，培训数据
	@RequestMapping(value="/selectTotalData",produces="application/json;charset=utf-8")
	public @ResponseBody String selectTotalData(String year,String month,int page,int limit)
	{	
		if(year==null||month==null)
		{
            Calendar calendar=Calendar.getInstance();
			year=String.valueOf(calendar.get(Calendar.YEAR));
			month =String.valueOf(calendar.get(Calendar.MONTH) + 1); 
		}
		else if(year.equals("")||month.equals(""))
		{
			Calendar calendar=Calendar.getInstance();
			year=String.valueOf(calendar.get(Calendar.YEAR));
			month =String.valueOf(calendar.get(Calendar.MONTH) + 1);
		}
		int before=limit*(page-1);
		List<TotalData> totalDataList=totalDataService.selectTotalData(year, month,before,limit);
		int count=totalDataService.countTotalData();
		JSONArray json=JSONArray.fromObject(totalDataList);
		String js=json.toString();
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	
}

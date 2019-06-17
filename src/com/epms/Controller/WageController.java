package com.epms.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.TotalData;
import com.epms.Bean.Wage;
import com.epms.Service.TotalDataService;
import com.epms.Service.WageService;

@Controller
public class WageController {

	@Autowired
	private WageService wageService;
	@Autowired
	private TotalDataService totalDataService;
	
	@RequestMapping(value="/showWageByJobId",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showWageByJobId(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Wage> eilist=wageService.showWageByJobId(jobId,before,limit);
		int count=wageService.countByJobId(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//计算工资
	//@Scheduled(cron="0 0 1 5 * ?")
	public  void addInitPerform(){
		long s=System.currentTimeMillis();//获取当前时间
		
		SimpleDateFormat df1 = new SimpleDateFormat("yyyy");//设置日期格式
        String year=df1.format(new Date());// new Date()为获取当前系统时间
        SimpleDateFormat df2 = new SimpleDateFormat("M");//设置日期格式
        String nowMonth=df2.format(new Date());
        int lastMonth=Integer.parseInt(nowMonth)-1;
        String month=String.valueOf(lastMonth);
        
		List<TotalData> totalData=totalDataService.selectMonthTotalData(year,month);

		//计算工资
		wageService.countStaffWage(totalData);
		
		long e=System.currentTimeMillis();//获取结束时间
		System.out.println("运行时间："+(e-s)+"ms");//输出程序运行时间
		System.out.println("----------------");
	}
	
	
	@RequestMapping(value="/showVerifyWage",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showVerifyWage(int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Wage> eilist=wageService.showVerifyWage(before,limit);
		int count=wageService.countVerifyWage();
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}

package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.Wage;
import com.hjh.Service.WageService;

@Controller
public class WageController {

	@Autowired
	private WageService wageService;
	
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
}

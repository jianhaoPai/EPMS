package com.epms.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.CultivateRecord;
import com.epms.Service.CultivateRecordService;


@Controller
@RequestMapping(value="CultivateRecordController")
public class CultivateRecordController {
	@Autowired
	private CultivateRecordService cultivateRecordService;
	
	//员工报名培训计划
	@RequestMapping(value = "insertCultivateRecord", method = RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertCultivateRecord(int id,HttpSession session) 
	{		
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		String result=cultivateRecordService.insertCultivateRecord(id,jobId); 
		return result;
	}
	
	//员工查询自己报名的培训课程
	@RequestMapping(value = "/selectCultivateRecordByJobId", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectCultivateRecordByJobId(int page, int limit, HttpSession session) 
	{
		int before = limit * (page - 1);
		// 带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateRecord> cultivateRecordlist = 
				cultivateRecordService.selectCultivateRecordByJobId(before, limit, jobId);
		int count = cultivateRecordService.countSelectCultivateRecordByJobId(jobId);
		// 用json来传值
		JSONArray json = JSONArray.fromObject(cultivateRecordlist);
		String js = json.toString();
		// 转为layui需要的json格式，必须要这一步
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	//上级查询下属报名的培训课程
	@RequestMapping(value = "/selectAllCultivateRecord", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectAllCultivateRecord(int page,int limit,HttpSession session) 
	{
		int before = limit * (page - 1);
		// 带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<CultivateRecord> cultivateRecordlist = 
				cultivateRecordService.selectCultivateRecordByJobId(before, limit, jobId);
		int count = cultivateRecordService.countSelectCultivateRecordByJobId(jobId);
		// 用json来传值
		JSONArray json = JSONArray.fromObject(cultivateRecordlist);
		String js = json.toString();
		// 转为layui需要的json格式，必须要这一步
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

}

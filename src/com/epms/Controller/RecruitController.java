package com.epms.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Recruit;
import com.epms.Service.RecruitService;
import com.epms.Tools.SendResumeMail;

//招聘计划发布
@Controller
@RequestMapping(value = "RecruitController")
public class RecruitController {
	@Autowired
	private RecruitService recruitService;

	@Autowired
	private Recruit recruit;

	@RequestMapping(value = "/login")
	public String login(Integer jobId, HttpSession session) 
	{
		session.setAttribute("jobId", jobId);
		return "whiteA";
	}
	
	//提交招聘计划
	@RequestMapping(value = "insertRecruit", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertRecruit(@Valid Recruit recruit,BindingResult error,HttpSession session)
	{
		if(recruit.getFunctionIntrduce().length()<=0||recruit.getDemand().length()<=0||recruit.getSum()<=0)
		{
			return null;
		}
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
	    recruit.setWriteId(jobId);
	    String result=recruitService.insertRecruit(recruit);
		return result;
	}
	
	//上级查询下级提交的招聘计划
	@RequestMapping(value="/selectAllRecruit",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruit(String occupationId,String departmentId,String status,int page,int limit,HttpSession session)
	{
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<Recruit> recruitlist=recruitService.selectAllRecruit(occupationId,departmentId,status,before, limit, jobId);
		int count=recruitService.countAllRecruit(occupationId,departmentId,status,jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//审核招聘计划
	@RequestMapping(value = "updateRecruitStatus", method = RequestMethod.POST)
	public @ResponseBody String updateRecruitStatus(Recruit recruit) 
	{		
		return recruitService.updateRecruitStatus(recruit);
	}
	
	//下级查询自己提交给上级的全部招聘计划
	@RequestMapping(value="/selectAllRecruitByWriteId",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruitByWriteId(String departmentId,String occupationId,String status,int page,int limit,HttpSession session){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<Recruit> recruitlist=recruitService.selectAllRecruitByWriteId(departmentId,occupationId,status,before, limit, jobId);
		int count=recruitService.countSelectAllRecruitByWriteId(departmentId,occupationId,status,jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//员工查询招聘计划
	@RequestMapping(value="/selectAllRecruitToEmployee",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllRecruitToEmployee(String occupationId,String departmentId,int page,int limit)
	{
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Recruit> recruitlist=recruitService.selectAllRecruitToEmployee(occupationId,departmentId,before, limit);
		int count=recruitService.countSelectAllRecruitToEmployee(occupationId,departmentId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(recruitlist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}

	

}


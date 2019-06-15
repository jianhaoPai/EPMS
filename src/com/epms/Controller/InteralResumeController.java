package com.epms.Controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.InteralResume;
import com.epms.Service.InteralResumeService;

//内部人员申请操作
@Controller
@RequestMapping(value = "InteralResumeController")
public class InteralResumeController {
	@Autowired
	private InteralResumeService interalResumeService;

	@Autowired
	private InteralResume interalResume;
	
	//提交简历
	@RequestMapping(value = "insertInteralResume",method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertInteralResume(@Valid InteralResume interalResume,BindingResult error,HttpSession session) 
	{		
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		interalResume.setJobId(jobId);
		if(error.hasErrors())
		{
			return null;
		}
		String result=interalResumeService.insertInteralResume(interalResume);
		return result;
	}
	
	@RequestMapping(value = "/login")
	public String login(Integer jobId, HttpSession session) 
	{
		session.setAttribute("jobId", jobId);
		return "whiteA";
	}

	//内部人员查询自己的简历
	@RequestMapping(value="/selectInteralResumeByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectInteralResumeByJobId(String departmentId,String occupationId,String status,int page,int limit,HttpSession session)
	{
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<InteralResume> interalResumeList=interalResumeService.selectInteralResumeByJobId(departmentId,occupationId,status,before, limit, jobId);
		int count=interalResumeService.countByJobId(departmentId,occupationId,status,jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(interalResumeList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	//部门经理和总经理查询简历信息
	@RequestMapping(value = "/selectAllInteralResume", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody String selectAllInteralResume(int page, int limit, HttpSession session) 
	{
		int before = limit * (page - 1);
		// 带参数从数据库里查询出来放到eilist的集合里
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		List<InteralResume> recruitlist = interalResumeService
				.selectAllInteralResume(before, limit, jobId);
		int count = interalResumeService.countAllInteralResume(jobId);
		// 用json来传值
		JSONArray json = JSONArray.fromObject(recruitlist);
		String js = json.toString();
		// 转为layui需要的json格式，必须要这一步
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}

}

package com.epms.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.Resume;
import com.epms.Service.ResumeService;


@Controller
@RequestMapping(value="ResumeController")
public class ResumeController {
	@Autowired
	private ResumeService resumeService;	
	@Autowired
	private Resume resume;
	
	
	//查询全部人员的简历
	@RequestMapping(value = "/selectAllResume", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllExternalResume(int page, int limit, HttpSession session) 
	{
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		int before = limit * (page - 1);
		// 带参数从数据库里查询出来放到eilist的集合里
		List list = resumeService.selectAllResumeByJobId(before, limit, jobId);
		int count = resumeService.countByJobId(jobId);
		// 用json来传值
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		// 转为layui需要的json格式，必须要这一步
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	// 审核简历
	@RequestMapping(value = "updateAllExternalResume", method = RequestMethod.POST)
	public @ResponseBody
	String updateAllExternalResume(Resume resume) 
	{
		return resumeService.updateAllExternalResume(resume);
	}

}



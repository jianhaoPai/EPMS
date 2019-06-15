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

import com.epms.Bean.CultivateApply;
import com.epms.Bean.ExternalResume;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.InteralResumeService;


//外部人员申请操作
@Controller
@RequestMapping(value="ExternalResumeController")
public class ExternalResumeController 
{

	@Autowired
	private InteralResumeService interalResumeService;
	
	@Autowired
	private ExternalResumeService externalResumeService;
	
	@Autowired
	private ExternalResume externalResume;
	
	//简历提交
	@RequestMapping(value = "insertExternalResume", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
	public @ResponseBody String insertExternalResume(@Valid ExternalResume externalResume,BindingResult error,HttpSession session)
	{
		if(error.hasErrors())
		{
			return null;
		}
		String result=externalResumeService.insertExternalResume(externalResume);
		return result;
	}
	
	//查询外部人员的简历
	@RequestMapping(value="/selectAllExternalResume",produces="application/json;charset=utf-8",method= RequestMethod.GET)
	public @ResponseBody String selectAllExternalResume(int page,int limit,HttpSession session)
	{
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<ExternalResume> externalResumelist=externalResumeService.selectAllExternalResume(before,limit,jobId);			
		int count=externalResumeService.countSelectAllExternalResume(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(externalResumelist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}

	

	
}


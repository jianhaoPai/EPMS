package com.epms.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.ExternalResume;
import com.epms.Bean.Resume;
import com.epms.Mapper.ResumeMapper;
import com.epms.Service.ExternalResumeService;
import com.epms.Service.ResumeService;
import com.epms.Tools.SendResumeMail;

@Controller
@RequestMapping(value="ResumeController")
public class ResumeController 
{
	@Autowired
	private ResumeService resumeService;	
	@Autowired 
	private Resume resume;
	@Autowired 
	private ExternalResumeService externalResumeService;
	
	@Autowired 
	private ResumeMapper resumeMapper;
	
	//查询全部人员的简历
	@RequestMapping(value = "/selectAllResume", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String selectAllExternalResume(String departmentId,String occupationId,String status,int page, int limit, HttpSession session) 
	{
		int jobId = Integer.parseInt(session.getAttribute("jobId").toString());
		int before = limit * (page - 1);
		// 带参数从数据库里查询出来放到eilist的集合里
		List list = resumeService.selectAllResumeByJobId(departmentId,occupationId,status,before, limit, jobId);
		int count = resumeService.countByJobId(departmentId,occupationId,status,jobId);
		// 用json来传值
		JSONArray json = JSONArray.fromObject(list);
		String js = json.toString();
		// 转为layui需要的json格式，必须要这一步
		String jso = "{\"code\":0,\"msg\":\"\",\"count\":" + count
				+ ",\"data\":" + js + "}";
		return jso;
	}
	
	// 审核简历
	@RequestMapping(value = "updateAllResume", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
	public @ResponseBody
	String updateAllResume(int id,String interviewDate,String status,String interviewName,String email) 
	{
		resume=new Resume();
		resume.setInterviewDate(interviewDate);
		resume.setInterviewId(id);
		resume.setId(id);
		resume.setStatus(status);
		String result =resumeService.updateAllResume(resume,interviewName);
		JSONObject result1 = new JSONObject();
		//实例化一个发送邮件的对象
		SendResumeMail mySendMail=new SendResumeMail();
		//根据邮箱找到该用户信息
		ExternalResume user= externalResumeService.getExternalResumeByEmail(email);
		Resume resume2=resumeMapper.selectResumeById(id);
		if(resume2.getApprovalDate()!=null&&resume2.getStatus()=="通过")
		{
				mySendMail.sendResume(email, "企业人事管理系统提醒，您的简历已通过"+"请于"+interviewDate+"下午两点参加面试");
				result1.put("status",true);
				result1.put("msg","发送成功");
			}else{
				mySendMail.sendResume(email, "企业人事管理系统提醒：很抱歉您的简历没有通过，感谢您对我们公司的认可！");
				result1.put("status",false);
				result1.put("msg","该邮箱不存在");
			}
		return result;
		
	}

}



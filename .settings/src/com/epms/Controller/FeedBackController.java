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

import com.epms.Bean.FeedBack;
import com.epms.Service.FeedBackService;

@Controller
@RequestMapping(value="FeedBackController")
public class FeedBackController 
{
	@Autowired
	private FeedBackService feedBackService;
	
	//存入反馈信息
	@RequestMapping(value="/insertFeedbackInfo",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertFeedbackInfo(@Valid FeedBack feedback,BindingResult error,HttpSession session)
	{
		if(error.hasErrors())
		{
			return null;
		}
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		feedback.setJobId(jobId);
		String result=feedBackService.insertFeedbackInfo(feedback);
		return result;
	}
	
	//上级查询下级提交的所有反馈信息
	@RequestMapping(value="/selectAllFeedBack",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllFeedBack(String state,String department_id,String feedback_type,
			                                      int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<FeedBack> feedBackList=feedBackService.selectAllFeedback(state, department_id, feedback_type, before, limit, jobId);
			
		int count=feedBackService.count(state, department_id, feedback_type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(feedBackList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//审批反馈信息
	@RequestMapping(value="/updateFeedBack",method=RequestMethod.POST)
	public @ResponseBody String updateFeedBack(FeedBack feedback)
	{
		String result=feedBackService.updateFeedback(feedback);
		return result;
	}
	
	//自己查询提交给上级的全部反馈信息
	@RequestMapping(value="/selectAllFeedBackByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllFeedBackByJobId(String state,String feedbackType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<FeedBack> feedBackList=feedBackService.selectAllFeedBackByJobId(state, feedbackType, before, limit, jobId);
			
		int count=feedBackService.countByJobId(state, feedbackType, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(feedBackList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}

package com.hjh.Controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.Apply;
import com.hjh.Bean.FeedBack;
import com.hjh.Service.ApplyService;

@Controller
@RequestMapping(value="ApplyController")
public class ApplyController 
{
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private Apply apply;
	
	//提交申请事项
	@RequestMapping(value="/insertApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertApply(@Valid Apply apply,BindingResult error,HttpSession session,
			                    @RequestParam(value="passiveName") String passiveName)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		apply.setWriteId(jobId);
		String result =applyService.insertApply(apply,passiveName);
		return result;
	}
	
	//查询全部直接下级提交的申请事项
	@RequestMapping(value="/selectAllApply",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllApply(String department_id,String state,String applytype_name,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Apply> applyList=applyService.selectAllApply(department_id, state, applytype_name, before, limit, jobId);
			
		int count=applyService.count(department_id, state, applytype_name, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(applyList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//审批申请事项
	@RequestMapping(value="/updateApply",method=RequestMethod.POST)
	public @ResponseBody String updateApply(Apply apply)
	{
		String result=applyService.updateApply(apply);
		return result;
	}
	
	//查询全部提交给直接上级的申请事项信息
	@RequestMapping(value="/selectAllApplyByWriteId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllApplyByWriteId(String department_id,String state,String applytype_name,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Apply> applyList=applyService.selectAllApplyByWriteId(department_id, state, applytype_name, before, limit, jobId);
			
		int count=applyService.countByWriteId(department_id, state, applytype_name, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(applyList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}

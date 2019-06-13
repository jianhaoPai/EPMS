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
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Vacate;
import com.epms.Service.VacateService;

@Controller
@RequestMapping(value="VacateController")
public class VacateController 
{
	@Autowired
	private VacateService vacateService;
	
	//提交请假事项
	@RequestMapping(value="submitVacateApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String submitVacateApply(@Valid Vacate vacate,BindingResult error,HttpSession session,ModelAndView mv)
	{
		if(error.hasErrors()||vacate.getStartDate().equals("")||vacate.getFinishDate().equals("")||vacate.getReason().equals(""))
		{
			return null;
		}
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		vacate.setJobId(jobId);
		String result=vacateService.insertVacate(vacate);
		session.setAttribute("vacate", vacate);
		return result;
	}
	
	//查询自己提交给直接上级的请假申请记录(自己查询)
	@RequestMapping(value="/selectAllVacateApplyByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllVacateApplyByJobId(String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		int after=page*limit;
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectAllVacateApplyByJobId(state, type, before, after, jobId);
		
		int count=vacateService.count(state, type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//查询全部直接下级提交的请假申请事项(上级查询下属的)
	@RequestMapping(value="/selectAllVacateApply",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllVacateApply(String department_id,String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectAllVacateApply(department_id, state, type, before, limit, jobId);
			
		int count=vacateService.countToLeader(department_id, state, type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//审批请假申请
	@RequestMapping(value="/updateVacateApply",method=RequestMethod.POST)
	public @ResponseBody String updateApply(Vacate vacate)
	{
		String result=vacateService.updateVacate(vacate);
		return result;
	}
	
	//根据工号仅查询审批通过的请假信息
	@RequestMapping(value="/selectPassVacateByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectPassVacateByJobId(int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacate> vacateList=vacateService.selectPassVacateByJobId(before, limit, jobId);
			
		int count=vacateService.countPassVacateByJobId(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(vacateList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//销假处理 
	@RequestMapping(value="/cancelVacateApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String cancelVacateApply(Vacate vacate)
	{
		String result=vacateService.cancelVacateApply(vacate);
		return result;
	}

}

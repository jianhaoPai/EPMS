package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.Apply;
import com.hjh.Bean.OccupationAdjust;
import com.hjh.Service.OccupationAdjustService;

@Controller
@RequestMapping(value="OccupationAdjustController")
public class OccupationAdjustController 
{
	@Autowired
	private OccupationAdjustService occupationAdjustService;
	
	//提交薪资调动申请表
	@RequestMapping(value="insertOccupationAdjust",produces="application/json;charset=utf-8")
	public @ResponseBody String insertOccupationAdjust(@Valid OccupationAdjust occupationAdjust,BindingResult error,String passiveName,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		if(error.hasErrors())
		{
			return null;
		}
		else
		{
			occupationAdjust.getApply().setWriteId(jobId);
			String result =occupationAdjustService.insertOccupationAdjust(occupationAdjust, passiveName);
			return result;
		}
	}
	
	//查询全部提交给直接上级的职位调动申请信息
	@RequestMapping(value="/selectAllOccupationAdjustByWriteId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjustByWriteId(String pre_departmentid,
		   String state,String type, int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllOccupationAdjustByWriteId(pre_departmentid, state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countByWriteId(pre_departmentid, state, type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//上级查询全部直接下属提交的职位调动申请信息
	@RequestMapping(value="/selectAllOccupationAdjuest",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjuest(String pre_departmentid,String state,String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllSalaryAdjust(pre_departmentid, state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countAllOccupationAdjust(pre_departmentid, state, type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//审批薪资调动申请
	@RequestMapping(value="/updateOccupationAdjuest",method=RequestMethod.POST)
	public @ResponseBody String updateSalaryAdjust(OccupationAdjust occupationAdjust,String state)
	{
		String result=occupationAdjustService.updateOccupationAdjust(occupationAdjust, state);
		return result;
	}
	
	//根据工号查询相关的职位调动记录
	@RequestMapping(value="/selectAllOccupationAdjustByJobId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllOccupationAdjustByJobId(String state,String type,
			             int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<OccupationAdjust> occupationAdjustList=occupationAdjustService.selectAllOccupationAdjustByJobId(state, type, before, limit, jobId);
			
		int count=occupationAdjustService.countByJobId(state, type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(occupationAdjustList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}

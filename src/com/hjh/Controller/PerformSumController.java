package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.PerformSum;
import com.hjh.Service.PerformSumService;

@Controller
public class PerformSumController {

	@Autowired
	private PerformSumService performSumService;
	
	@RequestMapping(value="/showAllPerform",produces="application/json;charset=utf-8;")
	@ResponseBody
	public String showAllPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<PerformSum> eilist=performSumService.showAllPerform(jobId,before,limit);
		int count=performSumService.count(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/showVerifyPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showManagerVerifyPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		String identity=performSumService.findIdentityByJobId(jobId);
		List<PerformSum> eilist=null;
		int count=0;
		if(identity.equals("董事")){
			eilist=performSumService.showDirectorVerifyPerform(jobId,before,limit);
			count=performSumService.countDirectorVerifyPerform(jobId);
		}else if(identity.equals("总经理")){
			eilist=performSumService.showGeneralManagerVerifyPerform(jobId,before,limit);
			count=performSumService.countManagerVerifyPerform(jobId);
		}else{//部门经理
			eilist=performSumService.showVerifyPerform(jobId,before,limit);
			count=performSumService.countStaffVerifyPerform(jobId);
		}
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/updateVerifyPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateVerifyPerformYes(HttpSession session,int jobId,String assessDate){
		
		int approverId=Integer.parseInt(session.getAttribute("jobId").toString());

		String result="";
		int i=performSumService.updateVerifyPerform(jobId,assessDate,approverId);
		
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/updateVerifyPerformNo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String updateVerifyPerformNo(HttpSession session,int jobId,String assessDate){
		//int approverId=Integer.parseInt(session.getAttribute("jobId").toString());
		String result="";
		int num = performSumService.updateVerifyPerformNo(jobId,assessDate);
		if(num==1){
			result="true";
			return result;
		}else{
			result="false";
			return result;
		}
	}
	
	@RequestMapping(value="/showStaffPerform",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showStaffPerform(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		String identity=performSumService.findIdentityByJobId(jobId);
		List<PerformSum> eilist=null;
		int count=0;
		
		if(identity.equals("董事")){
			eilist=performSumService.showDirectorStaffPerform(jobId,before,limit);
			count=performSumService.countDirectorStaffPerform(jobId);
		}else if(identity.equals("总经理")){
			eilist=performSumService.showGeneralManagerStaffPerform(jobId,before,limit);
			count=performSumService.countGeneralManagerStaffPerform(jobId);
		}else{//部门经理
			eilist=performSumService.showManagerStaffPerform(jobId,before,limit);
			count=performSumService.countManagerStaffPerform(jobId);
		}
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}

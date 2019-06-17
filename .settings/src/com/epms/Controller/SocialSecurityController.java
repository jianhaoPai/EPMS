package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Apply;
import com.epms.Bean.Attendance;
import com.epms.Bean.SocialSecurity;
import com.epms.Service.ApplyService;
import com.epms.Service.SocialSecurityService;

@Controller
@RequestMapping(value="SocialSecurityController")
public class SocialSecurityController 
{
	@Autowired
	private SocialSecurityService socialSecurityService;
	
	@Autowired
	private SocialSecurity socialSecurity;
	
	//查询自己全部保险信息
	@RequestMapping(value="/selectSocialSecurity",produces="application/json;charset=utf-8")
	public @ResponseBody String selectSocialSecurity(String securityType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		socialSecurity.setJobId(jobId);
		List<SocialSecurity> socialSecurityList=socialSecurityService.selectSocialSecurityById(securityType, before, limit, jobId);
		
		int count=socialSecurityService.count(securityType, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(socialSecurityList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	//根据保险类型查询保险购买信息
	@RequestMapping(value="selectSocialSecurityByTypeAndId")
	public ModelAndView selectSocialSecurityByTypeAndId(SocialSecurity socialSecurity,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		socialSecurity.setJobId(jobId);	
		List<SocialSecurity> socialSecurityList=socialSecurityService.selectSocialSecurityByTypeAndId(socialSecurity);
		if(socialSecurityList.isEmpty())
		{
			mv.addObject("selectSocialSecurityByTypeAndId", "暂无所查保险信息");
			mv.setViewName("socialSecurity");
		}
		else
		{
			mv.addObject("socialSecurityList", socialSecurityList);
			mv.setViewName("selectAllSocialSecurity");
		}
		return mv;
	}
	
}

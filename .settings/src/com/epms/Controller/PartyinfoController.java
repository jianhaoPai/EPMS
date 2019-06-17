package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Communication;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Service.CommunicationService;
import com.epms.Service.PartyinfoService;

@Controller
@RequestMapping(value="PartyinfoController")
public class PartyinfoController 
{
	@Autowired
	private PartyinfoService partyinfoService;
	
	@Autowired
	private Partyinfo partyinfo;
	
	//自己查询党信息
	@RequestMapping(value="selectPartyinfo")
	public ModelAndView selectPartyinfo(HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		partyinfo=partyinfoService.selectPartyinfoById(jobId);
		mv.addObject("partyinfo", partyinfo);
		mv.setViewName("partyinfo");
		return mv;
	}
	
}

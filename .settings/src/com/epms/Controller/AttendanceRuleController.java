package com.epms.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.AttendanceRule;
import com.epms.Service.AttendanceRuleService;

@Controller
@RequestMapping(value="AttendanceRuleController")
public class AttendanceRuleController 
{
	@Autowired
	private AttendanceRuleService attendanceRuleService;
	
	@Autowired
	private AttendanceRule attendanceRule;
	
	//修改上下班规定时间
	@RequestMapping(value="/updateAttendanceRule",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String updateAttendanceRule(@Valid AttendanceRule attendanceRule,BindingResult error)
	{
		if(error.hasErrors())
		{
			return null;
		}
		else
		{
			String result=attendanceRuleService.updatetAttendanceRuleAll(attendanceRule);
			return result;
		}
	}
	
	//查询上下班规定时间
	@RequestMapping(value="/selectAttendanceRule",method=RequestMethod.GET)
	public ModelAndView selectAttendanceRule(ModelAndView mv)
	{
		attendanceRule=attendanceRuleService.selectAttendanceRule();
		mv.addObject("attendanceRule", attendanceRule);
		mv.setViewName("Manager/updateAttendanceRule");
		return mv;
	}
}

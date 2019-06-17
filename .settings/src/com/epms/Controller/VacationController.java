package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Vacation;
import com.epms.Service.VacationService;

@Controller
@RequestMapping(value="VacationController")
public class VacationController 
{
	@Autowired
	private VacationService vacationService;
	
	//查询全部假期剩余信息
	@RequestMapping(value="/selectAllRemain",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllRemain(String type,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Vacation> vacationList=vacationService.selectVacationRemain(type, before, limit, jobId);
		int count=vacationService.count(type, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(vacationList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//通过类型和工号查询假期剩余信息
	@RequestMapping(value="selectRemainByType")
	public ModelAndView selectRemainByType(Vacation vacation,ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		vacation.setJobId(jobId);
		
		if(vacationService.selectRemainByVacation(vacation)!=null)
		{
			vacation=vacationService.selectRemainByVacation(vacation);
			mv.addObject("vacation", vacation);
		}
		else
		{
			mv.addObject("vacationMess", "暂无假期剩余信息！");
			mv.addObject("vacation", null);
		}
		mv.setViewName("selectAllVacationRemain");
		return mv;
	}

}

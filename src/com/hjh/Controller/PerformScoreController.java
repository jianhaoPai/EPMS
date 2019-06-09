package com.hjh.Controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.PerformScore;
import com.hjh.Service.PerformScoreService;
import com.hjh.Service.UserService;

@Controller
public class PerformScoreController {
	@Autowired
	private PerformScoreService performScoreService;
	@Autowired
	private UserService userService;
	/*
	 * 显示待评价记录
	 */
	@RequestMapping(value="/showAllEvaluate",produces="application/json;charset=utf-8")
	public @ResponseBody String showAllEvaluate(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<PerformScore> eilist=performScoreService.showAllEvaluate(jobId,before,limit);
		int count=performScoreService.count(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	/*
	 * 添加评价
	 */
	@RequestMapping(value="/addPerform",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public  String addPerform(int evaluateId,String assessDate,String type,PerformScore ps,HttpSession session){
		long s=System.currentTimeMillis();//获取开始时间
		String result="false";
		//计算总分
		int score=ps.getAssess1Score()+ps.getAssess2Score()+ps.getAssess3Score()+ps.getAssess4Score()+ps.getAssess5Score()+ps.getAssess6Score()+ps.getAssess7Score()+ps.getAssess8Score()+ps.getAssess9Score()+ps.getAssess10Score()
				+ps.getAssess11Score()+ps.getAssess12Score()+ps.getAssess13Score()+ps.getAssess14Score()+ps.getAssess15Score()+ps.getAssess16Score()+ps.getAssess17Score()+ps.getAssess18Score()+ps.getAssess19Score()+ps.getAssess20Score();
		//获取session中的工号
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		//添加绩效评价
		int pj=performScoreService.addPerform(jobId,evaluateId,score,assessDate,ps.getAssess1Score(),ps.getAssess2Score(),ps.getAssess3Score(),ps.getAssess4Score(),ps.getAssess5Score(),ps.getAssess6Score(),ps.getAssess7Score(),ps.getAssess8Score(),ps.getAssess9Score(),ps.getAssess10Score()
				,ps.getAssess11Score(),ps.getAssess12Score(),ps.getAssess13Score(),ps.getAssess14Score(),ps.getAssess15Score(),ps.getAssess16Score(),ps.getAssess17Score(),ps.getAssess18Score(),ps.getAssess19Score(),ps.getAssess20Score());
		//查询是否最后的评价，是则计算总绩效评价
		performScoreService.checkEndPerform(evaluateId,assessDate,type);
		if(pj==1){
			result="true";
		}
		long e=System.currentTimeMillis();//获取结束时间
		System.out.println("controller的运行时间："+(e-s)+"ms");//输出程序运行时间
		System.out.println("----------------");
		return result;
	}
	
	//添加绩效评价初始数据
	//@Scheduled(cron="0 0 1 1 * ?")
	public  void addInitPerform(){
		long s=System.currentTimeMillis();//获取开始时间
		System.out.println("绩效评价开始初始化数据");
		
		//初始化绩效评价数据
		performScoreService.addInitPerform();
		
		long e=System.currentTimeMillis();//获取结束时间
		System.out.println("controller的运行时间："+(e-s)+"ms");//输出程序运行时间
		System.out.println("----------------");
	}
}

package com.hjh.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hjh.Bean.Attendance;
import com.hjh.Service.AttendanceService;

@Controller
@RequestMapping(value="AttendanceController")
public class AttendanceController 
{
	@Autowired
	private AttendanceService attendanceService;
	
	@Autowired
	private Attendance attendance;
	
	//上班打卡
	@RequestMapping(value="/CheckIn",method=RequestMethod.GET,produces="application/json;charset=utf-8")
	@ResponseBody
	public String CheckIn(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		String checkMess=attendanceService.insertInAttendance(jobId);
		JSONObject result = new JSONObject();
		result.put("status", true);
		result.put("msg", checkMess);
		return result.toString();
	}
	
	//下班打卡
	@RequestMapping(value="/CheckOut",produces="application/json;charset=utf-8")
	@ResponseBody
	public String CheckOut(ModelAndView mv,HttpSession session)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		String checkMess=attendanceService.insertOutAttendance(jobId);
		JSONObject result = new JSONObject();
		result.put("status", true);
		result.put("msg", checkMess);
		return result.toString();
	}
	
	//查询根据工号查询全部打卡信息
	@RequestMapping(value="/SelectAllAttendance",produces="application/json;charset=utf-8")
	public @ResponseBody String SelectAllAttendance(String today,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Attendance> attendanceList=attendanceService.selectAllById(before,limit,jobId,today);
		
		int count=attendanceService.count(jobId, today);
		//用json来传值
		JSONArray json=JSONArray.fromObject(attendanceList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	/*//模糊查询总共迟到、早退、加班时长。
	@RequestMapping(value="/selectAttendanceSumDataByDate",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAttendanceSumDataByDate(String startDate,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		int after=page*limit;
		//带参数从数据库里查询出来放到eilist的集合里
		List<Attendance> attendanceList=attendanceService.selectSumDataByDate(before, after, startDate);
		
		int count=attendanceService.countSumDataByDate(startDate);
		//用json来传值
		JSONArray json=JSONArray.fromObject(attendanceList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}*/
	
}

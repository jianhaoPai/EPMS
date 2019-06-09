package com.hjh.Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.Personalinfo;
import com.hjh.Bean.RewardPunish;
import com.hjh.Service.RewardPunishService;

@Controller
public class RewardPunishController {
	@Autowired
	private RewardPunishService rewardPunishService;
	
	@RequestMapping(value="/showRewardPunish",produces="application/json;charset=utf8")
	@ResponseBody
	public String showRewardPunish(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<RewardPunish> eilist=rewardPunishService.showRewardPunish(jobId,before,limit);
		int count=rewardPunishService.countshowRewardPunish(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	
	@RequestMapping(value="/addStaffRewardPunish",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addStaffRewardPunish(HttpSession session,@Valid RewardPunish rewardPunish,Errors errors,String name) throws ParseException{
		
		JSONObject result = new JSONObject();
		if(errors.hasErrors()){
			//获取错误信息
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//打印字段错误信息
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
				result.put("status", false);
				result.put("msg", error.getDefaultMessage());
				return null;
			}
		}else{
			int applyId=Integer.parseInt(session.getAttribute("jobId").toString());
			Personalinfo personalInfo=rewardPunishService.selectUserById(rewardPunish.getJobId(),name,applyId);
			if(personalInfo!=null){
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				String setDate=sdf.format(new Date());
				int i=rewardPunishService.addStaffRewardPunish(rewardPunish.getJobId(),rewardPunish.getType(),rewardPunish.getContent(),rewardPunish.getReason(),setDate,applyId);
				if(i==1){
					result.put("status",true);
					result.put("msg", "添加员工奖惩成功");
					return result.toString();
				}else{
					result.put("status",false);
					result.put("msg", "添加员工奖惩失败");
					return result.toString();
				}
			}else{
				result.put("status",false);
				result.put("msg", "请正确输入部门内员工的工号和姓名");
				return result.toString();
			}
		}
		
		
		return result.toString();
	}
	
	@RequestMapping(value="/showVerifyRewardPunish",produces="application/json;charset=utf8")
	@ResponseBody
	public String showVerifyRewardPunish(int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<RewardPunish> eilist=rewardPunishService.showVerifyRewardPunish(before,limit);
		int count=rewardPunishService.countVerifyRewardPunish();
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/verifyRewardPunishYes",produces="application/json;charset=utf-8")
	@ResponseBody
	public String verifyRewardPunishYes(HttpSession session,int jobId,String reason,String setDate){
		int approveId=Integer.parseInt(session.getAttribute("jobId").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String approveDate = sdf.format(new Date());
		String result="";
		int i=rewardPunishService.verifyRewardPunishYes(jobId,reason,setDate,approveId,approveDate);
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/verifyRewardPunishNo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String verifyRewardPunishNo(HttpSession session,int jobId,String reason,String setDate){
		int approveId=Integer.parseInt(session.getAttribute("jobId").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String approveDate = sdf.format(new Date());
		String result="";
		int i=rewardPunishService.verifyRewardPunishNo(jobId,reason,setDate,approveId,approveDate);
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/showApplyRewardPunishRecord",produces="application/json;charset=utf8")
	@ResponseBody
	public String showApplyRewardPunishRecord(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		int after=page*limit;
		//带参数从数据库里查询出来放到eilist的集合里
		List<RewardPunish> eilist=rewardPunishService.showApplyRewardPunishRecord(jobId,before,after);
		int count=rewardPunishService.countApplyRewardPunishRecord(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/showVerifyRewardPunishRecord",produces="application/json;charset=utf8")
	@ResponseBody
	public String showVerifyRewardPunishRecord(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		int after=page*limit;
		//带参数从数据库里查询出来放到eilist的集合里
		List<RewardPunish> eilist=rewardPunishService.showVerifyRewardPunishRecord(jobId,before,after);
		int count=rewardPunishService.countVerifyRewardPunishRecord(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
}

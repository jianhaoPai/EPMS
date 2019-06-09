package com.hjh.Controller;

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
import com.hjh.Bean.Reimbursement;
import com.hjh.Service.ReimbursementService;

@Controller
public class ReimbursementController {

	@Autowired
	private ReimbursementService reimbursementService;
	
	@RequestMapping(value="/showReimbursementByJobId",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showReimbursementByJobId(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Reimbursement> eilist=reimbursementService.showReimbursementByJobId(jobId,before,limit);
		int count=reimbursementService.countByJobId(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/addReimburse",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String addReimburse(HttpSession session,@Valid Reimbursement reimbursement,Errors errors){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
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
			Reimbursement re = reimbursementService.findByJobIdTypeContent(reimbursement.getJobId(),reimbursement.getType(), reimbursement.getContent());
			if(re==null){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String date = sdf.format(reimbursement.getTime());

				int i = reimbursementService.addReimburse(jobId,reimbursement.getType(),reimbursement.getContent(),date,reimbursement.getAmount());
				if(i==1){
					result.put("status", true);
					result.put("msg", "添加成功");
				}
			}else{
				result.put("status", false);
				result.put("msg", "添加失败");
			}
		}
		
		
		return result.toString();
	}
	
	@RequestMapping(value="/showVerifyReimburse",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showVerifyReimburse(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Reimbursement> eilist=reimbursementService.showVerifyReimburse(before,limit);
		int count=reimbursementService.countVerifyReimburse();
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/verifyPerformYes",produces="application/json;charset=utf-8")
	@ResponseBody
	public String verifyPerformYes(HttpSession session,int jobId,String time){
		
		int verifyId=Integer.parseInt(session.getAttribute("jobId").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String verifyDate = sdf.format(new Date());
		
		String result="";
		int i=reimbursementService.verifyPerformYes(jobId,time,verifyId,verifyDate);
		
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/verifyPerformNo",produces="application/json;charset=utf-8")
	@ResponseBody
	public String verifyPerformNo(HttpSession session,int jobId,String time){
		
		int verifyId=Integer.parseInt(session.getAttribute("jobId").toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String verifyDate = sdf.format(new Date());
		
		String result="";
		int i=reimbursementService.verifyPerformNo(jobId,time,verifyId,verifyDate);
		
		if(i==1){
			result="true";
			return result;
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/showVerifyReimburseRecord",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showVerifyReimburseRecord(HttpSession session,int page,int limit){
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Reimbursement> eilist=reimbursementService.showVerifyReimburseRecord(jobId,before,limit);
		int count=reimbursementService.countVerifyReimburseRecord(jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}

}

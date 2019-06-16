package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.User;
import com.epms.Service.UserService;
import com.epms.Utils.SendMail;


@Controller

public class UserController {
	@Autowired
	private UserService userService;
	

	//@Scheduled(cron="*/10 * * * * ?")
	public void print() {
		System.out.println("插入数据库");
		userService.test(444,"444");
	}
	
	@RequestMapping(value="/showAllUser",produces="application/json;charset=utf-8")
	public @ResponseBody String showAllUser(String jobId,String name,String departmentName,int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<User> eilist=userService.findAllUser(jobId,name,departmentName,before,limit);
		int count=userService.count(jobId,name,departmentName);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(@Valid User user,Errors errors,Model model,HttpSession session){
		//调用service方法查询
		user=userService.checkLogin(user.getJobId(),user.getPassword());
		String result="false";
		if(errors.hasErrors()){
			//获取错误信息
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//打印字段错误信息
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
			}
		}else{
			if(user!=null){
				String username=userService.findNameByJobId(user.getJobId());
				session.setAttribute("jobId", user.getJobId());
				session.setAttribute("username", username);
				result="true";
			}else{
				result="false";
			}
		}
		
		return result;
	}
	
	
	@RequestMapping("/outLogin")
	public String outLogin(HttpSession session){
		//通过session.invalidata()方法来注销当前的session
        session.invalidate();
        return "redirect:/index.jsp";
	}
	
	@RequestMapping(value="/addAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String addAccount(@Valid User user,Errors errors,String name,String denameId,String ocnameId){
		String password=user.getPassword();
		JSONObject result = new JSONObject();
		if(errors.hasErrors()){
			//获取错误信息
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//打印字段错误信息
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
				return null;
			}
		}else{
			System.out.print(denameId);
			int jobid=userService.selectLastJobId(denameId)+1;
			//查询用户是否存在
			User addUser=userService.findAccountByJobId(jobid);
			//返回结果
			if(addUser==null){
				if(userService.addAccount(jobid,name,denameId,ocnameId, password)==1){
					result.put("status", true);
					result.put("msg", "添加成功");
				}else{
					result.put("status", false);
					result.put("msg", "工号错误");
				}
				
			}else{
				result.put("status", false);
				result.put("msg", "账号已存在");
			}
			
		}
		
		return result.toString();
	}
	
	@RequestMapping(value="/editAccount",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	@ResponseBody
	public String editAccount(Model model,String jobId,String password,String state,String denameId,String ocnameId){
		int jobid=Integer.parseInt(jobId);
		JSONObject data = new JSONObject();
		if(userService.editAccount(jobid,password,state,denameId,ocnameId)==1){
			data.put("status", true);
			data.put("msg", "修改成功");
		}else{
			data.put("status", false);
			data.put("msg", "修改失败");
		}
		return data.toString();
	}
	
	@RequestMapping(value="/resetPassword",produces="application/json;charset=utf-8")
	@ResponseBody
	public String resetPassword(String jobId,String email){
		JSONObject result = new JSONObject();
		//实例化一个发送邮件的对象
		SendMail mySendMail=new SendMail();
		//根据邮箱找到该用户信息

		User user= userService.getUserByEmail(jobId,email);
		
		if(user!=null) {
			mySendMail.sendMail(email, "企业人事管理系统提醒，您的密码为："+user.getPassword());
			result.put("status",true);
			result.put("msg","恭喜，找回密码成功");
		}else{
			result.put("status",false);
			result.put("msg","该邮箱不存在");
		}
		return result.toString();
	}
	
}

package com.epms.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.Role;
import com.epms.Service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value="/showRightsGroups",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRightsGroups(int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Role> eilist=roleService.showRightsGroups(before,limit);
		int count=roleService.count();
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/addRightsGroups",method=RequestMethod.POST)
	@ResponseBody
	public String addRightsGroups(@Valid Role role,Errors errors,HttpSession session){
		String result="false";
		//查询权限组是否存在
		int addRole=roleService.findGroupsByName(role.getName());
		if(errors.hasErrors()){
			//获取错误信息
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//打印字段错误信息
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
				return null;
			}
		}else{
			if(addRole==0){
				//获取当前日期
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		        String nowTime=df.format(new Date());// new Date()为获取当前系统时间
		        //获取添加者的工号
		        int createId=Integer.parseInt(session.getAttribute("jobId").toString());
		        //添加权限组
				roleService.addRightsGroups(role.getName(),role.getContent(),createId,nowTime);
				result="true";
			}else{
				result="false";
			}
		}
		return result;
	}
	
	@RequestMapping(value="/editRightsGroups",method=RequestMethod.POST)
	@ResponseBody
	public String editRightsGroups(@Valid Role role,Errors errors,String oldName){
		String result="false";
		if(errors.hasErrors()){
			//获取错误信息
			List<FieldError>errorList=errors.getFieldErrors();
			for(FieldError error:errorList){
				//打印字段错误信息
				System.err.println("field:"+error.getField()+"\t"+"msg:"+error.getDefaultMessage());
			}
			return null;
		}else{
			//更新权限组
			int i=roleService.editRightsGroups(role.getName(),role.getContent(),oldName);
			if(i==1){
				result="true";
			}else{
				result="false";
			}
		}
		
		
		return result;
	}
	
	@RequestMapping(value="/showRightsGroupsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRightsGroupsPeople(String id,int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<String> eilist=roleService.showRightsGroupsPeople(id,before,limit);
		int count=roleService.countPeople(id);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/deleteRightsGroupsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteRightsGroupsPeople(int jobId,String rName){
		String result="false";
		int i=roleService.deleteRightsGroupsPeople(jobId,rName);
		if(i==1){
			result="true";
		}else{
			result="false";
		}
		return result;
	}
}

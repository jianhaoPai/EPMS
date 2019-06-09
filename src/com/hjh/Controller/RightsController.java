package com.hjh.Controller;

import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjh.Bean.Rights;
import com.hjh.Bean.User;
import com.hjh.Service.RightsService;

@Controller
public class RightsController {

	@Autowired
	private RightsService rightsService;
	
	@RequestMapping(value="/showRights",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRights(String menuName,int page,int limit){
		int before=limit*(page-1);

		//带参数从数据库里查询出来放到eilist的集合里
		List<Rights> eilist=rightsService.showRights(menuName,before,limit);
		
		int count=rightsService.count(menuName);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/showRightsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showRightsPeople(String id,String jobId,String name,String dename, int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<User> eilist=rightsService.showRightsPeople(id,jobId,name,dename,before,limit);
		int count=rightsService.countRightsPeople(id,jobId,name,dename);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/addRightsPeople",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addRightsPeople(String jobId,String id){
		JSONObject result = new JSONObject();
		int i=rightsService.addRightsPeople(jobId,id);
		if(i==1){
			result.put("status", true);
		}else{
			result.put("status", false);
		}
		
		return result.toString();
	}
	
	@RequestMapping(value="/showGroupsRights",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showGroupsRights(String id,int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Rights> eilist=rightsService.showGroupsRights(id,before,limit);
		int count=rightsService.countGroupsRights(id);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/deleteGroupsRights",produces="application/json;charset=utf-8")
	@ResponseBody
	public String deleteGroupsRights(String menuId,String id){
		String result="false";
		int i=rightsService.deleteGroupsRights(menuId,id);
		if(i==1){
			result="true";
		}else{
			result="false";
		}
		return result;
	}
	
	@RequestMapping(value="/showGroupsLackRights",produces="application/json;charset=utf-8")
	@ResponseBody
	public String showGroupsLackRights(String id,String menuName,int page,int limit){
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		List<Rights> eilist=rightsService.showGroupsLackRights(id,menuName,before,limit);
		int count=rightsService.countGroupsLackRights(id,menuName);
		//用json来传值
		JSONArray json=JSONArray.fromObject(eilist);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	@RequestMapping(value="/addGroupsLackRights",produces="application/json;charset=utf-8")
	@ResponseBody
	public String addGroupsLackRights(String menuId,String id){
		JSONObject result = new JSONObject();
		if(rightsService.selectGroupsRights(menuId,id)==null){
			int i=rightsService.addGroupsLackRights(menuId,id);
			if(i==1){
				result.put("status", true);
				result.put("msg", "添加成功");
			}else{
				result.put("status", false);
				result.put("msg", "添加失败");
			}
		}else{
			result.put("status", false);
			result.put("msg", "权限已存在");
		}
		return result.toString();
	}
}

package com.epms.Controller;

import java.util.List;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.epms.Bean.Menu;
import com.epms.Service.MenuService;
@Controller
public class MenuController {
	@Autowired
	private MenuService menuService;
	
	/*
	 * 动态生成菜单栏
	 */
	@RequestMapping(value="/showMenuByRole",method={RequestMethod.POST},produces="application/json; charset=utf-8")
	public @ResponseBody String showMenuByRole(int jobId){
		//查询该用户所拥有的的菜单
		List<Menu> menu=menuService.findMenuByRole(jobId);
		//将查询数据转换为JSON格式
		JSONArray json=JSONArray.fromObject(menu);
		String js=json.toString();
		return js;
	}
	
	/*
	 * 返回需要登录状态的jsp页面
	 */
	@RequestMapping(value="requestPage")
	public ModelAndView requestPage(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}
	
	/*
	 * 返回不需要登录状态的jsp页面
	 */
	@RequestMapping(value="requestPageNoLogin")
	public ModelAndView requestPageNoLogin(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}
	
	/*
	 * 返回左侧菜单页面
	 */
	@RequestMapping(value="requestMainPage")
	public ModelAndView requestMainPage(ModelAndView mv,String page){
		mv.setViewName(page);
		return mv;
		
	}

}

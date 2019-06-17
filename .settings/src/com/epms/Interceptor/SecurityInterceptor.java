package com.epms.Interceptor;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Service.MenuService;

public class SecurityInterceptor implements HandlerInterceptor{

	@Autowired
	private MenuService menuService;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception{
		HttpSession session = request.getSession();
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
        String page=request.getParameter("page");
        //查询该用户的所属的权限组中是否有该权限
        int pathNum=menuService.findMenuByJobId(jobId,page);
        if(page.equals("main")||page.equals("homePage")){
        	return true;
        }else{
        	if(pathNum>0){
    			return true;
    		}else{
    			response.sendRedirect(request.getContextPath()+"/404.jsp");
    			return false;
    		}
        }
		
	}
}

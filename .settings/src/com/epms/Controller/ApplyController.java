package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.json.JSONArray;


import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.epms.Bean.Apply;
import com.epms.Service.ApplyService;

@Controller
@RequestMapping(value="ApplyController")
public class ApplyController 
{
	@Autowired
	private ApplyService applyService;
	
	@Autowired
	private Apply apply;
	
	//�ύ��������
	@RequestMapping(value="/insertApply",method=RequestMethod.POST,produces="application/json;charset=utf-8")
	public @ResponseBody String insertApply(@Valid Apply apply,BindingResult error,HttpSession session,
			                    @RequestParam(value="passiveName") String passiveName)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		apply.setWriteId(jobId);
		String result =applyService.insertApply(apply,passiveName);
		return result;
	}
	
	//��ѯȫ��ֱ���¼��ύ����������
	@RequestMapping(value="/selectAllApply",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllApply(String department_id,String state,String applytype_name,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//��������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Apply> applyList=applyService.selectAllApply(department_id, state, applytype_name, before, limit, jobId);
			
		int count=applyService.count(department_id, state, applytype_name, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(applyList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//������������
	@RequestMapping(value="/updateApply",method=RequestMethod.POST)
	public @ResponseBody String updateApply(Apply apply)
	{
		String result=applyService.updateApply(apply);
		return result;
	}
	
	//��ѯȫ���ύ��ֱ���ϼ�������������Ϣ
	@RequestMapping(value="/selectAllApplyByWriteId",produces="application/json;charset=utf-8")
	public @ResponseBody String selectAllApplyByWriteId(String department_id,String state,String applytype_name,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//��������ݿ����ѯ�����ŵ�eilist�ļ�����
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Apply> applyList=applyService.selectAllApplyByWriteId(department_id, state, applytype_name, before, limit, jobId);
			
		int count=applyService.countByWriteId(department_id, state, applytype_name, jobId);
		//��json����ֵ
		JSONArray json=JSONArray.fromObject(applyList);
		String js=json.toString();
		//תΪlayui��Ҫ��json��ʽ������Ҫ��һ��
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
}

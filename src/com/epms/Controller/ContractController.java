package com.epms.Controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.epms.Bean.Attendance;
import com.epms.Bean.Communication;
import com.epms.Bean.Contract;
import com.epms.Bean.Partyinfo;
import com.epms.Bean.Personalinfo;
import com.epms.Service.CommunicationService;
import com.epms.Service.ContractService;
import com.epms.Service.PartyinfoService;

@Controller
@RequestMapping(value="ContractController")
public class ContractController 
{
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private Contract contract;
	
	//查询合同信息
	@RequestMapping(value="/selectContract",produces="application/json;charset=utf-8")
	public @ResponseBody String selectContract(String startDate,String contractType,int page,int limit,HttpSession session)
	{	
		int before=limit*(page-1);
		//带参数从数据库里查询出来放到eilist的集合里
		
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		List<Contract> contractList=contractService.selectContractById(startDate, contractType, before, limit, jobId);
		
		int count=contractService.count(startDate, contractType, jobId);
		//用json来传值
		JSONArray json=JSONArray.fromObject(contractList);
		String js=json.toString();
		//转为layui需要的json格式，必须要这一步
		String jso="{\"code\":0,\"msg\":\"\",\"count\":"+count+",\"data\":"+js+"}";
		return jso;
	}
	
	//根据合同开始时间查询合同信息
	@RequestMapping(value="selectContractByDate")
	public ModelAndView selectContractByDate(Contract contract,HttpSession session,ModelAndView mv)
	{
		int jobId=Integer.parseInt(session.getAttribute("jobId").toString());
		contract.setJobId(jobId);
		
		List<Contract> contractList=contractService.selectContractByDate(contract);
		if(contractList.isEmpty())
		{
			mv.addObject("selectContractMess", "暂无所查合同信息！");
			mv.setViewName("contractList");
		}
		else
		{
			mv.addObject("contractList", contractList);
			mv.setViewName("contractList");
		}
		return mv;
	}
	
}

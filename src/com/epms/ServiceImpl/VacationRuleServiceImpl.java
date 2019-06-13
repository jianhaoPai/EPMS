package com.epms.ServiceImpl;

import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.VacationRule;
import com.epms.Mapper.VacationRuleMapper;
import com.epms.Service.VacationRuleService;

@Service("vacationRuleService")
public class VacationRuleServiceImpl implements VacationRuleService
{
	@Autowired
	private VacationRuleMapper vacationRuleMapper;
	
	@Autowired
	private VacationRule vacationeRule;
	
	//修改假期规定天数
	@Override
	public String updatetVacationRule(VacationRule vacationRule) 
	{
		JSONObject result =new JSONObject();
		if(vacationRule.getDay()<0)
		{
			return null;
		}
		else
		{
			if(vacationRuleMapper.updatetVacationRule(vacationRule)>0)
			{
				result.put("status", true);
				result.put("message", "修改成功！");
			}
			else
			{
				result.put("status", false);
				result.put("message", "修改失败！");
			}
			return result.toString();
		}
	}

	//查询假期规定天数
	@Override
	public List<VacationRule> selectVacationRule(int before,int after) 
	{
		return vacationRuleMapper.selectVacationRule(before, after);
	}

	@Override
	public int count() 
	{
		return vacationRuleMapper.count();
	}

}

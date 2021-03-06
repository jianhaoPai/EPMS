package com.epms.ServiceImpl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epms.Bean.AttendanceRule;
import com.epms.Mapper.AttendanceRuleMapper;
import com.epms.Service.AttendanceRuleService;

@Service("attendanceRuleService")
public class AttendanceRuleServiceImpl implements AttendanceRuleService
{
	@Autowired
	private AttendanceRuleMapper attendanceRuleMapper;
	
	@Autowired
	private AttendanceRule attendanceRule;

	//查询上下班时间
	@Override
	public AttendanceRule selectAttendanceRule() 
	{
		return attendanceRuleMapper.selectAttendanceRule();
	}
	
	// 修改上下班时间
	@Override
	public String updatetAttendanceRuleAll(AttendanceRule attendanceRule) 
	{
		JSONObject result = new JSONObject();
		if(Integer.parseInt(attendanceRule.getSetFinish())>24||Integer.parseInt(attendanceRule.getSetFinish())<12
				||Integer.parseInt(attendanceRule.getSetStart())<0||Integer.parseInt(attendanceRule.getSetStart())>12)
		{
			return null;
		}
		else
		{
			attendanceRule.setId(1);
			if (attendanceRuleMapper.updatetAttendanceRuleAll(attendanceRule)>0) 
			{
				result.put("status", true);
				result.put("message", "修改成功！");
			} 
			else 
			{
				result.put("status", false);
				result.put("message", "修改失败，没进行修改！");
			}
		}
		return result.toString();
	}
}

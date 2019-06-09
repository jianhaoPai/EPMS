package com.hjh.ServiceImpl;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.AttendanceRule;
import com.hjh.Mapper.AttendanceRuleMapper;
import com.hjh.Service.AttendanceRuleService;

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
		if (attendanceRuleMapper.updatetAttendanceRuleAll(attendanceRule) > 0) 
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

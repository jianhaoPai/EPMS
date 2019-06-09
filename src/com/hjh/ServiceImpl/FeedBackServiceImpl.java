package com.hjh.ServiceImpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hjh.Bean.FeedBack;
import com.hjh.Bean.Personalinfo;
import com.hjh.Mapper.FeedBackMapper;
import com.hjh.Mapper.PersonalinfoMapper;
import com.hjh.Service.FeedBackService;


@Service("feedBackService")
public class FeedBackServiceImpl implements FeedBackService
{
	@Autowired
	private FeedBackMapper feedBackMapper;
	
	@Autowired
	private PersonalinfoMapper personalinfoMapper;

	//提交反馈表
	@Override
	public String insertFeedbackInfo(FeedBack feedback) 
	{	
		JSONObject result = new JSONObject();
		if(feedback.getReason().length()<=0)
		{
			return null;
		}
		else
		{
			if(feedback.getFeedbackType().equals(""))
			{
				result.put("status", false);
				result.put("message", "提交失败，未选择反馈类型！");
			}
			else
			{
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				feedback.setSubmitDate(dateFormat.format(new Date()));
				feedback.setState("待审核");
				if(feedBackMapper.checkIfRepeat(feedback)>0)
				{
					result.put("status", false);
					result.put("message", "提交失败，请勿重复提交！");
				}
				else
				{
					feedBackMapper.insertFeedbackInfo(feedback);
					result.put("status", true);
					result.put("message", "提交成功！");
				}
			}
		}
		return result.toString();
	}

	//根据部门id查询反馈信息
	@Override
	public List<FeedBack> selectAllFeedback(String state,String department_id,String feedback_type,
			                                int before,int after,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			int departmentId=personalinfo.getDepartment().getDepartmentId();
			return feedBackMapper.selectFeedbackByDepartmentId(state, department_id, feedback_type, before, after, departmentId, jobId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return feedBackMapper.selectFeedbackOnlyManager(state, department_id, feedback_type, before, after);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return feedBackMapper.selectFeedbackOnlyTotalManager(state, department_id, feedback_type, before, after);
		}
		return null;
	}
	
	//分页查询时，计算个数
	@Override
	public int count(String state,String department_id,String feedback_type,int jobId) 
	{
		Personalinfo personalinfo=personalinfoMapper.selectPersonalByIdNotEducation(jobId);
		if(personalinfo.getOccupation().getOccupationName().equals("部门经理"))
		{
			int departmentId=personalinfo.getDepartment().getDepartmentId();
			return feedBackMapper.countByDepartmentId(state, department_id, feedback_type, departmentId);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("总经理"))
		{
			return feedBackMapper.countOnlyManager(state, department_id, feedback_type);
		}
		else if(personalinfo.getOccupation().getOccupationName().equals("董事"))
		{
			return feedBackMapper.countOnlyTotalManager(state, department_id, feedback_type);
		}
		return 0;
	}

	//审批反馈信息表
	@Override
	public String updateFeedback(FeedBack feedback) 
	{
		//先检查是否已经审批过，若已审批过,则无法重新审批
		if(feedBackMapper.selectFeedbackById(feedback.getId()).getApprovalDate()==null)
		{
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			feedback.setApprovalDate(dateFormat.format(new Date()));
			feedBackMapper.updateFeedback(feedback);
			return "true";
		}
		else
		{
			return "false";
		}
	}
	
	//根据工号查询全部反馈信息
	@Override
	public List<FeedBack> selectAllFeedBackByJobId(String state,String feedbackType,int before, int after,int jobId) 
	{
		return feedBackMapper.selectAllFeedBackByJobId(state, feedbackType, before, after, jobId);
	}

	@Override
	public int countByJobId(String state,String feedbackType,int jobId) 
	{
		return feedBackMapper.countByJobId(state, feedbackType, jobId);
	}
}

package com.hjh.Service;

import java.util.List;

import com.hjh.Bean.FeedBack;

public interface FeedBackService 
{
	//插入反馈信息
	public String insertFeedbackInfo(FeedBack feedback);
	
	//上级查询下级提交的反馈信息
	public List<FeedBack> selectAllFeedback(String state,String department_id,String feedback_type,
			                                int before,int after,int jobId);
	public int count(String state,String department_id,String feedback_type,int jobId);
	
	//审批反馈信息
	String updateFeedback(FeedBack feedback);
	
	//自己查询提交给上级的全部反馈信息
	public List<FeedBack> selectAllFeedBackByJobId(String state,String feedbackType,
			                                       int before,int after,int jobId);
	public int countByJobId(String state,String feedbackType,int jobId);
}

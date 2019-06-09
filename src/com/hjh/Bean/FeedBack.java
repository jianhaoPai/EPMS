package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="feedBack")
public class FeedBack 
{
	private int id;
	private int jobId;
	private String reason;
	private String state;
	private String submitDate;
	private String approvalDate;
	private String feedbackType;
	public String getFeedbackType() {
		return feedbackType;
	}
	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public FeedBack() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "FeedBack [id=" + id + ", jobId=" + jobId + ", reason=" + reason
				+ ", state=" + state + ", submitDate=" + submitDate
				+ ", approvalDate=" + approvalDate + ", feedbackType="
				+ feedbackType + "]";
	}
	
	

}

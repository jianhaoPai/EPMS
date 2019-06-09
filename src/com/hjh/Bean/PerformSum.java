package com.hjh.Bean;


public class PerformSum {
	private int id;
	
	private int jobId;
	
	private double score;
	
	private String type;
	
	private String assessDate;
	
	private int approverId;
	
	private String apprivalDate; 
	
	public String getApprivalDate() {
		return apprivalDate;
	}

	public void setApprivalDate(String apprivalDate) {
		this.apprivalDate = apprivalDate;
	}

	private String state;

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

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAssessDate() {
		return assessDate;
	}

	public void setAssessDate(String assessDate) {
		this.assessDate = assessDate;
	}

	public int getApproverId() {
		return approverId;
	}

	public void setApproverId(int approverId) {
		this.approverId = approverId;
	}



	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

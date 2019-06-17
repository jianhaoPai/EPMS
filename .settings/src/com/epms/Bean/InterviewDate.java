package com.epms.Bean;

public class InterviewDate {
	private int interviewId;
	private String interviewDate;
	
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	
	
	@Override
	public String toString() {
		return "InterviewDate [interviewId=" + interviewId + ", interviewDate="
				+ interviewDate + "]";
	}
	
	public InterviewDate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

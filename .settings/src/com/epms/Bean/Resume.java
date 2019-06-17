package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="resume")
public class Resume 
{
	private int id;
	private String workExperience;
	private String submitDate;
	private String approvalDate;
	private String interviewDate; 
	private int interviewId;
	private String status;
	private Department toDepartment;
	private Occupation toOccupation;
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkExperience() {
		return workExperience;
	}
	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
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
	public String getInterviewDate() {
		return interviewDate;
	}
	public void setInterviewDate(String interviewDate) {
		this.interviewDate = interviewDate;
	}
	public int getInterviewId() {
		return interviewId;
	}
	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}
	public Department getToDepartment() {
		return toDepartment;
	}
	public void setToDepartment(Department toDepartment) {
		this.toDepartment = toDepartment;
	}
	public Occupation getToOccupation() {
		return toOccupation;
	}
	public void setToOccupation(Occupation toOccupation) {
		this.toOccupation = toOccupation;
	}
	@Override
	public String toString() {
		return "Resume [id=" + id + ", workExperience=" + workExperience
				+ ", submitDate=" + submitDate + ", approvalDate="
				+ approvalDate + ", interviewDate=" + interviewDate
				+ ", interviewId=" + interviewId + ", toDepartment="
				+ toDepartment + ", toOccupation=" + toOccupation + "]";
	}
	public Resume() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

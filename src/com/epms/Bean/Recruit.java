package com.epms.Bean;

import org.springframework.stereotype.Component;

//招聘发布
@Component(value="recruit")
public class Recruit {
	private int id;
	private int writeId;
	private String functionIntrduce;
	private int sum;
	private String demand;
	private String submitDate;
	private String approvalDate;
	private String status;
	private Department department;
	private Occupation occupation;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWriteId() {
		return writeId;
	}
	public void setWriteId(int writeId) {
		this.writeId = writeId;
	}
	public String getFunctionIntrduce() {
		return functionIntrduce;
	}
	public void setFunctionIntrduce(String functionIntrduce) {
		this.functionIntrduce = functionIntrduce;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getDemand() {
		return demand;
	}
	public void setDemand(String demand) {
		this.demand = demand;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Occupation getOccupation() {
		return occupation;
	}
	public void setOccupation(Occupation occupation) {
		this.occupation = occupation;
	}
	@Override
	public String toString() {
		return "Recruit [id=" + id + ", writeId=" + writeId
				+ ", functionIntrduce=" + functionIntrduce + ", sum=" + sum
				+ ", demand=" + demand + ", submitDate=" + submitDate
				+ ", approvalDate=" + approvalDate + ", status=" + status
				+ ", department=" + department + ", occupation=" + occupation
				+ "]";
	}
	public Recruit() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}

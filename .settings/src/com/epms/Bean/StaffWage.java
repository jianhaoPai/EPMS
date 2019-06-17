package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component("staffWage")
public class StaffWage 
{
	private int jobId;
	private double wage;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	@Override
	public String toString() {
		return "StaffWage [ jobId=" + jobId + ", wage=" + wage
				+ "]";
	}
	public StaffWage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

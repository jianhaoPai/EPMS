package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component("staffWage")
public class StaffWage 
{
	private int id;
	private int jobId;
	private double wage;
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
	public double getWage() {
		return wage;
	}
	public void setWage(double wage) {
		this.wage = wage;
	}
	@Override
	public String toString() {
		return "StaffWage [id=" + id + ", jobId=" + jobId + ", wage=" + wage
				+ "]";
	}
	public StaffWage() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

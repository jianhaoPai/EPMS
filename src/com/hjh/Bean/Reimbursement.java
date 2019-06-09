package com.hjh.Bean;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

public class Reimbursement {

	private int id;
	
	private int jobId;
	
	private String type;
	
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Past
	private Date time;
	
	private double amount;
	
	private String state;
	
	private int verifyId;
	
	private String verifyDate;

	public int getVerifyId() {
		return verifyId;
	}

	public void setVerifyId(int verifyId) {
		this.verifyId = verifyId;
	}

	public String getVerifyDate() {
		return verifyDate;
	}

	public void setVerifyDate(String verifyDate) {
		this.verifyDate = verifyDate;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}

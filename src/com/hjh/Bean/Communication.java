package com.hjh.Bean;


import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

//通讯录表
@Component(value="communication")
public class Communication
{
	private int jobId;
	
	private String officePhone;
	
	@Pattern(regexp ="^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$",message="请输入一个合法的邮箱")
	private String email;
	
	private String officeAddress;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public Communication() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Communication [jobId=" + jobId + ", officePhone=" + officePhone
				+ ", email=" + email + ", officeAddress=" + officeAddress + "]";
	}
}

package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="socialSecurity")
public class SocialSecurity 
{
	private int jobId;
	private String securityType;
	private String buyDate;
	private String finishDate;
	private double companyMoney;
	private double privateMoney;
	
	
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getSecurityType() {
		return securityType;
	}
	public void setSecurityType(String securityType) {
		this.securityType = securityType;
	}
	public String getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(String buyDate) {
		this.buyDate = buyDate;
	}
	public double getCompanyMoney() {
		return companyMoney;
	}
	public void setCompanyMoney(double companyMoney) {
		this.companyMoney = companyMoney;
	}
	public double getPrivateMoney() {
		return privateMoney;
	}
	public void setPrivateMoney(double privateMoney) {
		this.privateMoney = privateMoney;
	}
	@Override
	public String toString() {
		return "SocialSecurity [jobId=" + jobId + ", securityType="
				+ securityType + ", buyDate=" + buyDate + ", finishDate="
				+ finishDate + ", companyMoney=" + companyMoney
				+ ", privateMoney=" + privateMoney + "]";
	}
	public SocialSecurity() {
		super();
		// TODO Auto-generated constructor stub
	}
}

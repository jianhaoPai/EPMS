package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="contract")
public class Contract
{
	private int jobId;
	private String startDate;
	private String finishDate;
	private String contractType;
	
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(String finishDate) {
		this.finishDate = finishDate;
	}
	@Override
	public String toString() {
		return "Contract [jobId=" + jobId + ", startDate=" + startDate
				+ ", finishDate=" + finishDate + ", contractType="
				+ contractType + "]";
	}
	public Contract() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

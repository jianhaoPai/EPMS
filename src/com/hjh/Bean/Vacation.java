package com.hjh.Bean;

import org.springframework.stereotype.Component;


//¼ÙÆÚÊ£Óà¼ÇÂ¼±í
@Component(value="vacation")
public class Vacation 
{
	private int jobId;
	private String type;
	private int remain;
	private int virtualUse;
	private int alreadyUse;
	
	public int getAlreadyUse() {
		return alreadyUse;
	}
	public void setAlreadyUse(int alreadyUse) {
		this.alreadyUse = alreadyUse;
	}
	public int getVirtualUse() {
		return virtualUse;
	}
	public void setVirtualUse(int virtualUse) {
		this.virtualUse = virtualUse;
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
	public int getRemain() {
		return remain;
	}
	public void setRemain(int remain) {
		this.remain = remain;
	}
	@Override
	public String toString() {
		return "Vacation [jobId=" + jobId + ", type=" + type + ", remain="
				+ remain + ", virtualUse=" + virtualUse + ", alreadyUse="
				+ alreadyUse + "]";
	}
	public Vacation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

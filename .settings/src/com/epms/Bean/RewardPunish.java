package com.epms.Bean;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

public class RewardPunish {
	@NotNull
	private int jobId;
	@NotBlank
	private String type;
	@NotBlank
	private String content;
	@NotBlank
	private String reason;
	
	private String setDate;
	
	private int applyId;
	
	private int approveId;
	
	private String approveDate;
	
	private String state;

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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getSetDate() {
		return setDate;
	}

	public void setSetDate(String setDate) {
		this.setDate = setDate;
	}

	public int getApplyId() {
		return applyId;
	}

	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}

	public int getApproveId() {
		return approveId;
	}

	public void setApproveId(int approveId) {
		this.approveId = approveId;
	}

	public String getApproveDate() {
		return approveDate;
	}

	public void setApproveDate(String approveDate) {
		this.approveDate = approveDate;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
}

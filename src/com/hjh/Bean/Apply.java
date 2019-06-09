package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="apply")
public class Apply
{
	private int id;
	private int writeId;
	private int passiveId;
	private String reason;
	private String submitDate;
	private String approvalDate;
	private String state;
	private Applytype applytype;
	
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
	public int getPassiveId() {
		return passiveId;
	}
	public void setPassiveId(int passiveId) {
		this.passiveId = passiveId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Applytype getApplytype() {
		return applytype;
	}
	public void setApplytype(Applytype applytype) {
		this.applytype = applytype;
	}
	@Override
	public String toString() {
		return "Apply [id=" + id + ", writeId=" + writeId + ", passiveId="
				+ passiveId + ", reason=" + reason + ", submitDate="
				+ submitDate + ", approvalDate=" + approvalDate + ", state="
				+ state + ", applytype=" + applytype + "]";
	}
	public Apply() {
		super();
		// TODO Auto-generated constructor stub
	}
}

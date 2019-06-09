package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="partyinfo")
public class Partyinfo
{
	private int jobId;
	private String partyOccupation;
	private String partyIdentity;
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getPartyOccupation() {
		return partyOccupation;
	}
	public void setPartyOccupation(String partyOccupation) {
		this.partyOccupation = partyOccupation;
	}
	public String getPartyIdentity() {
		return partyIdentity;
	}
	public void setPartyIdentity(String partyIdentity) {
		this.partyIdentity = partyIdentity;
	}
	@Override
	public String toString() {
		return "Partyinfo [jobId=" + jobId + ", partyOccupation="
				+ partyOccupation + ", partyIdentity=" + partyIdentity + "]";
	}
	public Partyinfo() {
		super();
		// TODO Auto-generated constructor stub
	}
}

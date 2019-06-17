package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="applytype")
public class Applytype 
{
	private int applytypeId;
	private String applytypeName;
	public int getApplytypeId() {
		return applytypeId;
	}
	public void setApplytypeId(int applytypeId) {
		this.applytypeId = applytypeId;
	}
	public String getApplytypeName() {
		return applytypeName;
	}
	public void setApplytypeName(String applytypeName) {
		this.applytypeName = applytypeName;
	}
	@Override
	public String toString() {
		return "Applytype [applytypeId=" + applytypeId + ", applytypeName="
				+ applytypeName + "]";
	}
	public Applytype() {
		super();
		// TODO Auto-generated constructor stub
	}
}

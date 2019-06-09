package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component(value="cultivateType")
public class CultivateType {
	
	private int cultivateId;
	private String cultivateName;
	public int getCultivateId() {
		return cultivateId;
	}
	public void setCultivateId(int cultivateId) {
		this.cultivateId = cultivateId;
	}
	public String getCultivateName() {
		return cultivateName;
	}
	public void setCultivateName(String cultivateName) {
		this.cultivateName = cultivateName;
	}
	@Override
	public String toString() {
		return "CultivateType [cultivateId=" + cultivateId + ", cultivateName="
				+ cultivateName + "]";
	}
	public CultivateType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}

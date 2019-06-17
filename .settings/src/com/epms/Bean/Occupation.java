package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="occupation")
public class Occupation
{
	private int occupationId;
	private String occupationName;
	
	@Override
	public String toString() {
		return "Occupation [occupationId=" + occupationId + ", occupationName="
				+ occupationName + "]";
	}

	public int getOccupationId() {
		return occupationId;
	}

	public void setOccupationId(int occupationId) {
		this.occupationId = occupationId;
	}

	public String getOccupationName() {
		return occupationName;
	}

	public void setOccupationName(String occupationName) {
		this.occupationName = occupationName;
	}

	public Occupation() {
		super();
		// TODO Auto-generated constructor stub
	}

}

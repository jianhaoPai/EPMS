package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="salaryAdjust")
public class SalaryAdjust 
{
	private int applyId;
	private Apply apply;
	private String salaryadjustType;
	private double salaryadjustMoneny;
	
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public Apply getApply() {
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	public String getSalaryadjustType() {
		return salaryadjustType;
	}
	public void setSalaryadjustType(String salaryadjustType) {
		this.salaryadjustType = salaryadjustType;
	}
	public double getSalaryadjustMoneny() {
		return salaryadjustMoneny;
	}
	public void setSalaryadjustMoneny(double salaryadjustMoneny) {
		this.salaryadjustMoneny = salaryadjustMoneny;
	}
	
	@Override
	public String toString() {
		return "SalaryAdjust [applyId=" + applyId + ", apply=" + apply
				+ ", salaryadjustType=" + salaryadjustType
				+ ", salaryadjustMoneny=" + salaryadjustMoneny + "]";
	}
	public SalaryAdjust() {
		super();
		// TODO Auto-generated constructor stub
	}
}

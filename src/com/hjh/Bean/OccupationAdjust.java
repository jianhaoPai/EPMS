package com.hjh.Bean;

import org.springframework.stereotype.Component;

@Component("occupationAdjust")
public class OccupationAdjust
{
	private int applyId;
	private String type;
	private int toDepartmentId;
	private int toOccupationId;
	private int preDepartmentId;
	private int preOccupationId;
	private Apply apply;
	
	public Apply getApply() {
		return apply;
	}
	public void setApply(Apply apply) {
		this.apply = apply;
	}
	public int getApplyId() {
		return applyId;
	}
	public void setApplyId(int applyId) {
		this.applyId = applyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getToDepartmentId() {
		return toDepartmentId;
	}
	public void setToDepartmentId(int toDepartmentId) {
		this.toDepartmentId = toDepartmentId;
	}
	public int getToOccupationId() {
		return toOccupationId;
	}
	public void setToOccupationId(int toOccupationId) {
		this.toOccupationId = toOccupationId;
	}
	public int getPreDepartmentId() {
		return preDepartmentId;
	}
	public void setPreDepartmentId(int preDepartmentId) {
		this.preDepartmentId = preDepartmentId;
	}
	public int getPreOccupationId() {
		return preOccupationId;
	}
	public void setPreOccupationId(int preOccupationId) {
		this.preOccupationId = preOccupationId;
	}
	@Override
	public String toString() {
		return "OccupationAdjust [applyId=" + applyId + ", type=" + type
				+ ", toDepartmentId=" + toDepartmentId + ", toOccupationId="
				+ toOccupationId + ", preDepartmentId=" + preDepartmentId
				+ ", preOccupationId=" + preOccupationId + ", apply=" + apply
				+ "]";
	}
	public OccupationAdjust() {
		super();
		// TODO Auto-generated constructor stub
	}
}

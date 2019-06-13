package com.epms.Bean;

import org.springframework.stereotype.Component;

@Component(value="cultivateApply")
public class CultivateApply {
	
	private int id;
	private int writeId;
	private int typeId;
	private int teacherId;
	private String startDate;
	private String finishDate;
	private String site;
	private double cost;
	private String submitDate;
	private String approvalDate;
	private String status;
	private int sum;
	private String introduce;
	private String trainName;
	private String facePeople;
	private int alreadyPerson;
	private Teacher teacher;
	private CultivateType cultivateType;
	private Department department;
	
	public int getAlreadyPerson() {
		return alreadyPerson;
	}
	public void setAlreadyPerson(int alreadyPerson) {
		this.alreadyPerson = alreadyPerson;
	}
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
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
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
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getFacePeople() {
		return facePeople;
	}
	public void setFacePeople(String facePeople) {
		this.facePeople = facePeople;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public CultivateType getCultivateType() {
		return cultivateType;
	}
	public void setCultivateType(CultivateType cultivateType) {
		this.cultivateType = cultivateType;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	@Override
	public String toString() {
		return "CultivateApply [id=" + id + ", writeId=" + writeId
				+ ", typeId=" + typeId + ", teacherId=" + teacherId
				+ ", startDate=" + startDate + ", finishDate=" + finishDate
				+ ", site=" + site + ", cost=" + cost + ", submitDate="
				+ submitDate + ", approvalDate=" + approvalDate + ", status="
				+ status + ", sum=" + sum + ", introduce=" + introduce
				+ ", trainName=" + trainName + ", facePeople=" + facePeople
				+ ", alreadyPerson=" + alreadyPerson + ", teacher=" + teacher
				+ ", cultivateType=" + cultivateType + ", department="
				+ department + "]";
	}
	public CultivateApply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}

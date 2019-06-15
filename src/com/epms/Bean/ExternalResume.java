package com.epms.Bean;

import org.springframework.stereotype.Component;

//外部人员简历表
@Component(value="externalResume")
public class ExternalResume {
	private int resumeId;
	private String name;
	private int age;
	private String sex;
	private String education;
	private String phone;
	private String email;
	private String address;
	private Resume resume;
	
	public Resume getResume() {
		return resume;
	}
	public void setResume(Resume resume) {
		this.resume = resume;
	}
	public int getResumeId() {
		return resumeId;
	}
	public void setResumeId(int resumeId) {
		this.resumeId = resumeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ExternalResume [resumeId=" + resumeId + ", name=" + name
				+ ", age=" + age + ", sex=" + sex + ", education=" + education
				+ ", phone=" + phone + ", email=" + email + ", address="
				+ address + ", resume=" + resume + "]";
	}
	public ExternalResume() {
		super();
		// TODO Auto-generated constructor stub
	}
}
	



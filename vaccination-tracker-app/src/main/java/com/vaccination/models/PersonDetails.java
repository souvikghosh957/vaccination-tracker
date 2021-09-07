package com.vaccination.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PersonDetails {
	
	@Id
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "adhar_id")
	private String adharId;
	@Column(name = "voter_id")
	private String voterId;
	@Column(name = "pan_id")
	private String panId;
	@Column(name = "age")
	private String age;
	@Column(name = "vaccine_count")
	private int vaccineCount;
	@Column(name = "member_count")
	private int memberCount;
	@Column(name = "area_name")
	private String areaName;
	@Column(name = "district")
	private String district;
	@Column(name = "state")
	private String state;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdharId() {
		return adharId;
	}
	public void setAdharId(String adharId) {
		this.adharId = adharId;
	}
	public String getVoterId() {
		return voterId;
	}
	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}
	public String getPanId() {
		return panId;
	}
	public void setPanId(String panId) {
		this.panId = panId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public int getVaccineCount() {
		return vaccineCount;
	}
	public void setVaccineCount(int vaccineCount) {
		this.vaccineCount = vaccineCount;
	}
	public int getMemberCount() {
		return memberCount;
	}
	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}

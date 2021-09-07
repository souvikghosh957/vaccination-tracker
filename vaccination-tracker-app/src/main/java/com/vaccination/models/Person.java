package com.vaccination.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
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
	private int age;

	@Column(name = "vaccine_count")
	private int vaccinCount;
	
	@Column(name = "family_id")
	private String familyId;

	@Column(name = "pin_code")
	private int pinCode;

//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "pin_code", referencedColumnName = "pin_code")
//	private Area area;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getVaccinCount() {
		return vaccinCount;
	}

	public void setVaccinCount(int vaccinCount) {
		this.vaccinCount = vaccinCount;
	}

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

}

package com.vaccination.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "area")
public class Area {
	@Id
	@Column(name = "pin_code")
	private int pinCode;

	@Column(name = "area_name")
	private String areaName;

	@Column(name = "district")
	private String district;

	@Column(name = "state")
	private String state;

	@Column(name = "citizen_count")
	private String citizenCount;

	@Column(name = "vaccinated_count")
	private String vaccinatedCount;

	@Column(name = "persentage")
	private String persentage;

	@Column(name = "family_count")
	private String familyCount;

	@Column(name = "area_zone_flag")
	private String areaZoneFlag;

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
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

	public String getCitizenCount() {
		return citizenCount;
	}

	public void setCitizenCount(String citizenCount) {
		this.citizenCount = citizenCount;
	}

	public String getVaccinatedCount() {
		return vaccinatedCount;
	}

	public void setVaccinatedCount(String vaccinatedCount) {
		this.vaccinatedCount = vaccinatedCount;
	}

	public String getPersentage() {
		return persentage;
	}

	public void setPersentage(String persentage) {
		this.persentage = persentage;
	}

	public String getFamilyCount() {
		return familyCount;
	}

	public void setFamilyCount(String familyCount) {
		this.familyCount = familyCount;
	}

	public String getAreaZoneFlag() {
		return areaZoneFlag;
	}

	public void setAreaZoneFlag(String areaZoneFlag) {
		this.areaZoneFlag = areaZoneFlag;
	}

}

package com.vaccination.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "family")
public class Family {
	@Id
	@Column(name = "family_id")
	private String familyId;

	@Column(name = "member_count")
	private int memberCount;

	@Column(name = "pin_code")
	private int pinCode;

	@Column(name = "vaccinated_count")
	private int vaccinatedCount;

	@Column(name = "family_zone_flag")
	private String familyZoneFlag;

	public String getFamilyId() {
		return familyId;
	}

	public void setFamilyId(String familyId) {
		this.familyId = familyId;
	}

	public int getMemberCount() {
		return memberCount;
	}

	public void setMemberCount(int memberCount) {
		this.memberCount = memberCount;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public int getVaccinatedCount() {
		return vaccinatedCount;
	}

	public void setVaccinatedCount(int vaccinatedCount) {
		this.vaccinatedCount = vaccinatedCount;
	}

	public String getFamilyZoneFlag() {
		return familyZoneFlag;
	}

	public void setFamilyZoneFlag(String familyZoneFlag) {
		this.familyZoneFlag = familyZoneFlag;
	}
}

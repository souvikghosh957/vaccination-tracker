package com.vaccination.models;

import org.springframework.lang.Nullable;

public class VaccinationRequest {

	private String name;
	@Nullable
	private String voterId;
	@Nullable
	private String adharId;
	@Nullable
	private String panId;
	private int vaccNo;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVoterId() {
		return voterId;
	}

	public void setVoterId(String voterId) {
		this.voterId = voterId;
	}

	public String getAdharId() {
		return adharId;
	}

	public void setAdharId(String adharId) {
		this.adharId = adharId;
	}

	public String getPanId() {
		return panId;
	}

	public void setPanId(String panId) {
		this.panId = panId;
	}

	public int getVaccNo() {
		return vaccNo;
	}

	public void setVaccNo(int vaccNo) {
		this.vaccNo = vaccNo;
	}
}

package com.vaccination.models;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GovtIdDetails {
	
	private String name;
	private Date dob;
	private String adharId;
	private String voterId;
	private String panId;
	
	public GovtIdDetails() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
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

	@Override
	public String toString() {
		return "GovtIdDetails [name=" + name + ", dob=" + dob + ", adharId=" + adharId + ", voterId=" + voterId
				+ ", panId=" + panId + "]";
	}
	
	

}

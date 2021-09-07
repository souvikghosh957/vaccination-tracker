package com.govt.id.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "id_details")
public class IdentityDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int Id;
	@Column(name = "name")
	private String name;
	@Column(name = "dob")
	private Date dob;
	@Column(name = "adahar_id")
	private String adharId;
	@Column(name = "voter_id")
	private String voterId;
	@Column(name = "pan_id")
	private String panId;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
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
	
	public IdentityDetails() {
		
	}

	public IdentityDetails(String name, Date dob, String adharId, String voterId, String panId) {
		super();
		this.name = name;
		this.dob = dob;
		this.adharId = adharId;
		this.voterId = voterId;
		this.panId = panId;
	}

}

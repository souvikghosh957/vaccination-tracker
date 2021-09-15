package com.vaccination.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(name = "username")
	String userName;
	@Column(name = "pass_word")
	String password;
	@Column(name = "is_active")
	boolean isActive;
	@Column(name = "role")
	String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	public Users() {
	
	}

	public Users(int id, String userName, String password, boolean isActive, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.isActive = isActive;
		this.role = role;
	}

}

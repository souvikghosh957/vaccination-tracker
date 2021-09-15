package com.vaccination.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vaccination.models.Users;

public class VaccinationUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private boolean isActive;
	private List<SimpleGrantedAuthority> authority;

	VaccinationUserDetails() {

	}

	public VaccinationUserDetails(Users user) {
		this.userName = user.getUserName();
		this.password = user.getPassword();
		this.isActive = user.getIsActive();
		this.authority = Arrays.stream(user.getRole().split(",")).map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList());

	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authority;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return isActive;
	}

}

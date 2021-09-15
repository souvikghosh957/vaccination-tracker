package com.vaccination.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vaccination.models.Users;
import com.vaccination.repository.UsersRepository;

@Service
public class VaccinationUserDetailsService implements UserDetailsService {

	@Autowired
	UsersRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = null;
		try {
		 user = userRepo.findByUserName(username);
		} catch(Exception ex) {
			System.out.println("Could not retrive the user details");
		}
		if (user == null)
			throw new UsernameNotFoundException("User not found with username: " + username);
		return new VaccinationUserDetails(user);
	}

}

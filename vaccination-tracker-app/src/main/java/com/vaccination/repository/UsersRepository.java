package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.models.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>{
	
	public Users findByUserName(String userName);

}

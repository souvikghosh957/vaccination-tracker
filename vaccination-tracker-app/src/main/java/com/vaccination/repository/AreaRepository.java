package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vaccination.models.Area;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
	
	public Area findByPinCode(Integer pinCode);

}

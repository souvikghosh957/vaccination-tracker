package com.govt.id.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.govt.id.model.IdentityDetails;

public interface GovtIdRepository extends JpaRepository<IdentityDetails, Integer>{
	
	@Query(value = "Select * from id_details where adahar_id = :adharId",  nativeQuery=true)
	public IdentityDetails findByAdharId(String adharId);
	
	public IdentityDetails findByVoterId(String voterId);
	
	public IdentityDetails findByPanId(String panId);

}

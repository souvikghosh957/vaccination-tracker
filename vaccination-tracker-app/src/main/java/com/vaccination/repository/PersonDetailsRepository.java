package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.models.PersonDetails;

@Repository
public interface PersonDetailsRepository extends JpaRepository<PersonDetails, Integer>{
	
	
	@Query(value="select p.id, p.name, p.adhar_id, p.voter_id, p.pan_id, p.age, p.vaccine_count, f.member_count, a.area_name, a.district, a.state from \r\n"
			+ "	person p inner join family f on p.family_id = f.family_id inner join area a on a.pin_code = p.pin_code where\r\n"
			+ "	p.adhar_id = :adharId or p.voter_id = :voterId or p.pan_id = :panId", nativeQuery=true)
	public PersonDetails getPersonsVaccinatedDetails(String adharId, String voterId, String panId);
	
}

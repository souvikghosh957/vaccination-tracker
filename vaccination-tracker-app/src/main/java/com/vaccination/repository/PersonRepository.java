package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.models.Person;
import com.vaccination.models.PersonDetails;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer>{
	
	@Modifying
	@Query(value="update Person set vaccinCount = :vacCount where adharId = :adharId")
	public int updatePersonByAdharId(String adharId, String vacCount);
	
	@Query(value="update person set vaccine_count = :vacCount where pan_id = :panId", nativeQuery=true)
	public int updatePersonByPanId(String panId, String vacCount);
	
	@Query(value="update person set vaccine_count = :vacCount where pan_id = :voterId", nativeQuery=true)
	public int updatePersonByVoterId(String voterId, String vacCount);
	
	@Query(value="select * from person p where p.adhar_id= :adharId", nativeQuery=true)
	public Person getPersonByAdharId(String adharId);
	
	@Query(value="select * from person p where p.pan_id= :panId", nativeQuery=true)
	public Person getPersonByPanId(String panId);
	
	@Query(value="select * from person p where p.voter_id= :voterId", nativeQuery=true)
	public Person getPersonByVoterId(String voterId);
	
	
	
	//
//	@Query(value="select * from author a where a.first_name= :firstName", nativeQuery=true)
//    List<Author> getAuthorsByFirstName(String firstName);
	
}

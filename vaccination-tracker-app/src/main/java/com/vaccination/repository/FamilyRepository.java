package com.vaccination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vaccination.models.Family;

@Repository
public interface FamilyRepository extends JpaRepository<Family, String> {
	
	@Modifying
	@Query(value="update family set vaccinated_count = :vacCount, family_zone_flag = :flag where family_id = :familyId", nativeQuery = true)
	public int updateFamilyVaccinatedCount(Integer vacCount, String flag, String familyId);

}

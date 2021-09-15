package com.vaccination.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.vaccination.helper.ExcelHelper;
import com.vaccination.models.Area;
import com.vaccination.models.Family;
import com.vaccination.models.GovtIdDetails;
import com.vaccination.models.Person;
import com.vaccination.models.PersonDetails;
import com.vaccination.models.VaccinationRequest;
import com.vaccination.repository.AreaRepository;
import com.vaccination.repository.FamilyRepository;
import com.vaccination.repository.PersonDetailsRepository;
import com.vaccination.repository.PersonRepository;
import com.vaccination.util.Pair;
import com.vaccination.util.Triplate;
import com.vaccination.util.VaccinationUtil;

@Service
public class VaccinationTrackerService {

	private static final Logger log = LoggerFactory.getLogger(VaccinationTrackerService.class);
	@Autowired
	PersonRepository personRepository;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	FamilyRepository familyRepository;
	
	@Autowired
	TriggerUpdate triggerUpdate;
	
	@Autowired
	PersonDetailsRepository personDetailsRepository;
	
	@Autowired
	RestTemplate restTemplate;

	@Transactional
	public void saveSurveyRecords(MultipartFile file) {
		
		try {
			Triplate<List<Area>, List<Family>, List<Person>> tuple = ExcelHelper.excelToEntity(file.getInputStream());

			List<Area> filteredArea = tuple.first.stream().filter(VaccinationUtil.distinctByKey(p -> p.getPinCode()))
					.collect(Collectors.toList());

			List<Family> filteredFamily = tuple.second.stream()
					.filter(VaccinationUtil.distinctByKey(p -> p.getFamilyId())).collect(Collectors.toList());

			areaRepository.saveAllAndFlush(filteredArea);
			familyRepository.saveAllAndFlush(filteredFamily);
			List<Person> existingPersons = personRepository.findAll();
			List<Person> filteredPersons = ExcelHelper.filterOutExistingRecords(existingPersons, tuple.third);
			personRepository.saveAllAndFlush(filteredPersons);
			triggerUpdate.updateRecords();
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}

	public List<Integer> updateRecords(List<VaccinationRequest> vaccinationRequest) throws Exception {

		List<Pair<Triplate<String, String, String>, String>> trpLst = ExcelHelper.getRequestTriplate(vaccinationRequest);
		List<Integer> updatedIds = new ArrayList<Integer>();
		for(Pair<Triplate<String, String, String>, String> trp: trpLst) {
			if(trp.first.first != null) {
				String adharId = trp.first.first;
				Person person = personRepository.getPersonByAdharId(adharId);			
				if (person.getVaccinCount() < 2) {
					int count = person.getVaccinCount() + 1;
					person.setVaccinCount(Integer.valueOf(count));
					personRepository.save(person);
					updatedIds.add(person.getId());
				}
				triggerUpdate.updateRecords();
				//personRepository.updatePersonByAdharId(adharId, trp.second);
			} else if(trp.first.second != null) {
				String panId = trp.first.second;
				Person person = personRepository.getPersonByPanId(panId);
				if (person.getVaccinCount() < 2) {
					int count = person.getVaccinCount() + 1;
					person.setVaccinCount(Integer.valueOf(count));
					personRepository.save(person);
					updatedIds.add(person.getId());
				}
				triggerUpdate.updateRecords();
				//personRepository.updatePersonByPanId(panId, trp.second);
			} else if (trp.first.third != null) {
				String voterId = trp.first.third;
				Person person = personRepository.getPersonByVoterId(voterId);
				if (person.getVaccinCount() < 2) {
					int count = person.getVaccinCount() + 1;
					person.setVaccinCount(Integer.valueOf(count));
					personRepository.save(person);
					updatedIds.add(person.getId());
				}
				triggerUpdate.updateRecords();
			//	personRepository.updatePersonByVoterId(voterId, trp.second);
			} else {
				throw new Exception("Please enter a valid Id");
			}
				
		}
		
		return updatedIds;
	}

	
	public PersonDetails getPersonDetails(String adharId, String voterId, String panId) throws Exception {
		GovtIdDetails govtIdDetails = null;
		
		String baseUrl = "http://localhost:8082/govt-id/getIdentityDetails";
		
		if(adharId != null && !adharId.isEmpty()) {
			baseUrl = baseUrl + "?adharId=" + adharId;
		} else if(voterId != null && !voterId.isEmpty()) {
			baseUrl = baseUrl + "?voterId=" + voterId;
		} else if(panId != null && !panId.isEmpty()) {
			baseUrl = baseUrl + "?panId=" + panId;
		}
		try {
		 govtIdDetails = restTemplate.getForObject(
				 baseUrl, GovtIdDetails.class);
	
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
		
		if(govtIdDetails != null) {
			log.info("Response: " +  govtIdDetails.toString());
			adharId = govtIdDetails.getAdharId();
			voterId = govtIdDetails.getVoterId();
			panId = govtIdDetails.getPanId();
		}
		
		PersonDetails personDetails = personDetailsRepository.getPersonsVaccinatedDetails(adharId, voterId, panId);
		
		if(govtIdDetails != null && personDetails != null) {
			personDetails.setAdharId(govtIdDetails.getAdharId());
			personDetails.setVoterId(govtIdDetails.getVoterId());
			personDetails.setPanId(govtIdDetails.getPanId());		
		}
        
		return personDetails;
	}

	public Area getAreaDetails(String pincode) {
		return areaRepository.findByPinCode(Integer.parseInt(pincode));
	}

	public Family getFamilyDetails(String familyId) {
		return familyRepository.findByFamilyId(familyId);
	}

}

package com.vaccination.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vaccination.models.Area;
import com.vaccination.models.Family;
import com.vaccination.models.Person;
import com.vaccination.repository.AreaRepository;
import com.vaccination.repository.FamilyRepository;
import com.vaccination.repository.PersonRepository;

@Service
public class TriggerUpdate {

	@Autowired
	PersonRepository personRepository;

	@Autowired
	AreaRepository areaRepository;

	@Autowired
	FamilyRepository familyRepository;

	@Async("VaccinationUpdateExecutor")
	public void updateRecords() {

		List<Person> persons = personRepository.findAll();

		Map<String, Long> vaccinatedFamilyCount = persons.stream().filter(it -> (it.getVaccinCount() >= 2))
				.collect(Collectors.groupingBy(Person::getFamilyId, Collectors.counting()));

		List<Family> familes = familyRepository.findAll();

		List<Family> updatedList = familes.stream().map(it -> {
			it.setVaccinatedCount(Integer.valueOf(vaccinatedFamilyCount.getOrDefault(it.getFamilyId(), 0l).toString()));
			return it;
		}).collect(Collectors.toList());

		updatedList.forEach(it -> familyRepository.save(it));

		List<Area> areas = areaRepository.findAll();

		Map<Integer, List<Family>> familyMapByPin = familes.stream().collect(Collectors.groupingBy(Family::getPinCode));

		for (Area area : areas) {
			List<Family> fmlLst = familyMapByPin.get(area.getPinCode());

			int citizen = 0;
			int vaccinated = 0;

			for (Family family : fmlLst) {
				citizen += family.getMemberCount();
				vaccinated += family.getVaccinatedCount();
			}
			float percentage = ((float)vaccinated / (float)citizen) * 100;
			int familyCount = fmlLst.size();
			area.setCitizenCount(String.valueOf(citizen));
			area.setVaccinatedCount(String.valueOf(vaccinated));
			area.setPersentage(String.valueOf(percentage));
			area.setFamilyCount(String.valueOf(familyCount));

			areaRepository.save(area);
		}

	}

}

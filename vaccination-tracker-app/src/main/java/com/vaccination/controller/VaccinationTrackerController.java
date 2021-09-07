package com.vaccination.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.vaccination.helper.ExcelHelper;
import com.vaccination.message.ResponseMessage;
import com.vaccination.models.PersonDetails;
import com.vaccination.models.PersonDetailsResponse;
import com.vaccination.models.VaccinationRequest;
import com.vaccination.service.VaccinationTrackerService;

@CrossOrigin("http://localhost:8081")
@RefreshScope
@RestController
@RequestMapping(value = "/vaccination")
public class VaccinationTrackerController {
	
	@Value("${test.message}")
	private String messageProp;

	@Autowired
	private VaccinationTrackerService vaccinationService;

	@PostMapping("/uploadRecords")
	public ResponseEntity<ResponseMessage> loadRecords(@RequestParam(name = "file") MultipartFile file) {
		String message = "";
		if (ExcelHelper.hasExcelFormat(file)) {
			try {
				vaccinationService.saveSurveyRecords(file);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}
		message = "Please upload an excel file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	@PostMapping("/updateVaccination")
	public ResponseEntity<ResponseMessage> updateVaccination(@RequestBody List<VaccinationRequest> vaccinationRequest) {
		String message = "";
		if(!vaccinationRequest.isEmpty()) {
			try {
				List<Integer> ids = vaccinationService.updateRecords(vaccinationRequest);
				message = "Upated Ids: " + ids;
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));	
			} catch(Exception e) {
				message = "Could not update the records!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}		
		}
		message = "Please provide non empty requests!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	
	@GetMapping("/getPersonVaccinationDetails")
	public ResponseEntity<PersonDetailsResponse> getIdentityDetails(
			@RequestParam(name = "adharId", required = false) String adharId,
			@RequestParam(name = "voterId", required = false) String voterId,
			@RequestParam(name = "panId", required = false) String panId) {
		String message = "";
		PersonDetailsResponse personDetailsResponse = null;
		try {
			if ((adharId != null && !adharId.isEmpty()) || (voterId != null && !voterId.isEmpty())
					|| (panId != null && !panId.isEmpty())) {
				PersonDetails personDetails = vaccinationService.getPersonDetails(adharId, voterId, panId);
				//message = "Success!";
				message = messageProp;
				personDetailsResponse = new PersonDetailsResponse(personDetails, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(personDetailsResponse);
			} else {
				message = "Please enter any of Aadhar, Voter or Pan Id.";
				personDetailsResponse = new PersonDetailsResponse(null, new ResponseMessage(message));
				return ResponseEntity.status(HttpStatus.OK).body(personDetailsResponse);
			}
		} catch (Exception e) {
			message = "Could not create the records!. Please contact support.";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
		}
	}

}

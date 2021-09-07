package com.govt.id.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.govt.id.message.ResponseMessage;
import com.govt.id.model.IdentityDetails;
import com.govt.id.service.GovtIdsService;

@CrossOrigin("http://localhost:8082")
@RefreshScope
@RestController
@RequestMapping(value = "/govt-id")
public class GovtIdController {
	
	@Autowired
	private GovtIdsService govtIdsService;
	
	@PostMapping("/createIdentity")
	public ResponseEntity<ResponseMessage> createIdentity(@RequestBody List<IdentityDetails> identityDetails) {
		String message = "";
			try {
				govtIdsService.createIdentity(identityDetails);
				message = "Record created successfully.";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				message = "Could not create the records!. Please contact support.";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
	
	

	@GetMapping("/getIdentityDetails")
	public ResponseEntity<IdentityDetails> getIdentityDetails(
			@RequestParam(name = "adharId", required = false) String adharId,
			@RequestParam(name = "voterId", required = false) String voterId,
			@RequestParam(name = "panId", required = false) String panId) {
		String message = "";
			try {
				IdentityDetails result = null;
				if(adharId != null && !adharId.isEmpty()) {
					result = govtIdsService.findByAdharId(adharId);
				} else if(voterId != null && !voterId.isEmpty()) {
					result = govtIdsService.findByVoterId(voterId);
				} else if(panId != null && !panId.isEmpty()) {
					result = govtIdsService.findByPanId(panId);
				} else {
					message = "Please pass either of adhar Id, Pan Id or voter Id";
				}			
				return ResponseEntity.status(HttpStatus.OK).body(result);
			} catch (Exception e) {
				message = "Could not create the records!. Please contact support.";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(null);
			}
		//return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}
}

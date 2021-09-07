package com.govt.id.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.govt.id.model.IdentityDetails;
import com.govt.id.repository.GovtIdRepository;

@Service
public class GovtIdsService {
	
	@Autowired
	GovtIdRepository govtIdRepository;
	
	public void createIdentity(List<IdentityDetails> identityDetails) {
		govtIdRepository.saveAllAndFlush(identityDetails);
	}
	
	public IdentityDetails findByAdharId(String adharId) {
		return govtIdRepository.findByAdharId(adharId);
	}
	
	public IdentityDetails findByVoterId(String voterId) {
		return govtIdRepository.findByVoterId(voterId);
	}
	
	public IdentityDetails findByPanId(String panId) {
		return govtIdRepository.findByPanId(panId);
	}

}

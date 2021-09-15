package com.vaccination.models;

import com.vaccination.message.ResponseMessage;

public class FamilyDetails {
	private Family family;
	private ResponseMessage responseMessage;
	public Family getFamilyDetails() {
		return family;
	}
	public void setFamilyDetails(Family family) {
		this.family = family;
	}
	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}
	public FamilyDetails(Family family, ResponseMessage responseMessage) {
		super();
		this.family = family;
		this.responseMessage = responseMessage;
	}

}

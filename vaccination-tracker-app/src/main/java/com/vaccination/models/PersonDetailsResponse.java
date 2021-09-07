package com.vaccination.models;

import com.vaccination.message.ResponseMessage;

public class PersonDetailsResponse {

	PersonDetails personDetails;

	ResponseMessage responseMessage;

	PersonDetailsResponse() {

	}

	public PersonDetails getPersonDetails() {
		return personDetails;
	}

	public void setPersonDetails(PersonDetails personDetails) {
		this.personDetails = personDetails;
	}

	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

	public PersonDetailsResponse(PersonDetails personDetails, ResponseMessage responseMessage) {
		super();
		this.personDetails = personDetails;
		this.responseMessage = responseMessage;
	}

}

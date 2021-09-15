package com.vaccination.models;

import com.vaccination.message.ResponseMessage;

public class AreaDetails {
	private Area area;
	private ResponseMessage message;
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public ResponseMessage getMessage() {
		return message;
	}
	public void setMessage(ResponseMessage message) {
		this.message = message;
	}
	
	public AreaDetails(Area area, ResponseMessage message) {
		super();
		this.area = area;
		this.message = message;
	}
}

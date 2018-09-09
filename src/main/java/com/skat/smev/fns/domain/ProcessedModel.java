package com.skat.smev.fns.domain;

public class ProcessedModel {

	private String date;
	private String responseNumber;
	private String returned;
	private boolean taken;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getResponseNumber() {
		return responseNumber;
	}

	public void setResponseNumber(String responseNumber) {
		this.responseNumber = responseNumber;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}

	public boolean isTaken() {
		return taken;
	}

	public void setTaken(boolean taken) {
		this.taken = taken;
	}

}
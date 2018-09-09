package com.skat.smev.fns.domain;

public class StatusMessageModel extends BaseMessageModel {

	private String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "StatusMessageModel [description=" + description + "]";
	}

}
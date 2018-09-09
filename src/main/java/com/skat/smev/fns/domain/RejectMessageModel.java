package com.skat.smev.fns.domain;

import java.util.List;

public class RejectMessageModel extends BaseMessageModel {

	private List<RejectModel> rejects;

	public List<RejectModel> getRejects() {
		return rejects;
	}

	public void setRejects(List<RejectModel> rejects) {
		this.rejects = rejects;
	}

	@Override
	public String toString() {
		return "RejectMessageModel [messageId=" + messageId + ", rejects="
				+ rejects + "]";
	}

}
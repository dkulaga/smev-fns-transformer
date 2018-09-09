package com.skat.smev.fns.domain;

import java.util.List;

/**
 * 
 * Модель json-запроса
 *
 */
public class RequestModel {

	private String requestId;
	private String regType;
	private String fnsRequestId;
	private List<AttachmentModel> attachments;

	public String getRequestId() {
		return requestId;
	}

	public String getRegType() {
		return regType;
	}

	public String getFnsRequestId() {
		return fnsRequestId;
	}

	public List<AttachmentModel> getAttachments() {
		return attachments;
	}
}
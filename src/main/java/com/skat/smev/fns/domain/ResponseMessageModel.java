package com.skat.smev.fns.domain;

import java.util.List;

public class ResponseMessageModel extends BaseMessageModel {

	private String requestId;
	private String fnsRequestId;
	private String code;
	private String statusDoc;
	private List<String> attachments;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getFnsRequestId() {
		return fnsRequestId;
	}

	public void setFnsRequestId(String fnsRequestId) {
		this.fnsRequestId = fnsRequestId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStatusDoc() {
		return code;
	}

	public void setStatusDoc(String code) {
		this.code = code;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	@Override
	public String toString() {
		return "ResponseMessageModel{" +
				"messageId='" + messageId + '\'' +
				", requestId='" + requestId + '\'' +
				", fnsRequestId='" + fnsRequestId + '\'' +
				", code='" + code + '\'' +
				'}';
	}
}
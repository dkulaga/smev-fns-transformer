package com.skat.smev.fns.domain;

/**
 * 
 * Модель json-запроса
 *
 */
public class StatusRequestModel {

	private String requestId;
	private String fnsRequestId;

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
}
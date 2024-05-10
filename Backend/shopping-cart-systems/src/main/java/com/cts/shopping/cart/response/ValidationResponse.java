package com.cts.shopping.cart.response;

import jakarta.persistence.Id;

import com.cts.shopping.cart.constants.Constants;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationResponse {
	@Id
	@JsonProperty
	private boolean validStatus;

	private Constants.ResponseStatus status_code;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public Constants.ResponseStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Constants.ResponseStatus status_code) {
		this.status_code = status_code;
	}

	public ValidationResponse(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public ValidationResponse(boolean validStatus, ResponseStatus status_code) {
		super();
		this.validStatus = validStatus;
		this.status_code = status_code;
	}

	public ValidationResponse() {
		super();
	}
}

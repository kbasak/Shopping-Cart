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
	private String username;

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ValidationResponse(boolean validStatus, ResponseStatus status_code) {
		// super();
		// this.validStatus = validStatus;
		this(validStatus, status_code, "Not Found");
	}

	public ValidationResponse(boolean validStatus, ResponseStatus status_code, String username) {
		super();
		this.validStatus = validStatus;
		this.status_code = status_code;
		this.username = username;
	}

	public ValidationResponse() {
		super();
	}

	@Override
	public String toString() {
		return "ValidationResponse [validStatus=" + validStatus + ", status_code=" + status_code + ", username="
				+ username + "]";
	}

}

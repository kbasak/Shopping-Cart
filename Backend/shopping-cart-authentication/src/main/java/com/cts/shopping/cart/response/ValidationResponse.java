package com.cts.shopping.cart.response;

import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationResponse {
	@Id
	@JsonProperty
	private boolean validStatus;
	private String username;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public ValidationResponse(boolean validStatus) {
		// super();
		// this.validStatus = validStatus;
		this(validStatus, "Not Found");
	}

	public ValidationResponse(boolean validStatus, String username) {
		super();
		this.validStatus = validStatus;
		this.username = username;
	}

	public ValidationResponse() {
		super();
	}

}

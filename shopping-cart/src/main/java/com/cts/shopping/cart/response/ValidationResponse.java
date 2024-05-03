package com.cts.shopping.cart.response;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationResponse {
	@Id
    @JsonProperty
    private boolean validStatus;

	public boolean isValidStatus() {
		return validStatus;
	}

	public void setValidStatus(boolean validStatus) {
		this.validStatus = validStatus;
	}

	public ValidationResponse(boolean validStatus) {
		super();
		this.validStatus = validStatus;
	}

	public ValidationResponse() {
		super();
	}
}

package com.cts.shopping.cart.response;

import com.cts.shopping.cart.constants.Constants;

public class RegistrationResponse {
	private String username;
	private Constants.ResponseStatus status_code;

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

	public RegistrationResponse(Constants.ResponseStatus status_code) {
		super();
		this.status_code = status_code;
	}

	public RegistrationResponse(String username, Constants.ResponseStatus status_code) {
		super();
		this.username = username;
		this.status_code = status_code;
	}

	public RegistrationResponse() {
		super();
	}

	@Override
	public String toString() {
		return "RegistrationResponse [username=" + username + ", status_code=" + status_code + "]";
	}

}

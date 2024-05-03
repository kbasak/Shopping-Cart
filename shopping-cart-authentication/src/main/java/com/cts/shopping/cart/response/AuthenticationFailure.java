package com.cts.shopping.cart.response;

import com.cts.shopping.cart.constants.Constants;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;

public class AuthenticationFailure {
	private String username;
	private String message;
	private Constants.ResponseStatus status_code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Constants.ResponseStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Constants.ResponseStatus status_code) {
		this.status_code = status_code;
	}

	public AuthenticationFailure(String username, String message, ResponseStatus status_code) {
		super();
		this.username = username;
		this.message = message;
		this.status_code = status_code;
	}

	public AuthenticationFailure() {
		super();
	}

	@Override
	public String toString() {
		return "AuthenticationFailure [username=" + username + ", message=" + message + ", status_code=" + status_code
				+ "]";
	}

}

package com.cts.shopping.cart.response;

import com.cts.shopping.cart.constants.Constants;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;

public class AuthenticationFailure {
	private String username;
	private String ErrorMessage;
	private Constants.ResponseStatus status_code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String ErrorMessage) {
		this.ErrorMessage = ErrorMessage;
	}

	public Constants.ResponseStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Constants.ResponseStatus status_code) {
		this.status_code = status_code;
	}

	public AuthenticationFailure(String username, String ErrorMessage, ResponseStatus status_code) {
		super();
		this.username = username;
		this.ErrorMessage = ErrorMessage;
		this.status_code = status_code;
	}

	public AuthenticationFailure() {
		super();
	}

	@Override
	public String toString() {
		return "AuthenticationFailure [username=" + username + ", ErrorMessage=" + ErrorMessage + ", status_code=" + status_code
				+ "]";
	}

}

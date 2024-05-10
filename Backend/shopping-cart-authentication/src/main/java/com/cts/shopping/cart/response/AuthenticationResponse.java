package com.cts.shopping.cart.response;

import com.cts.shopping.cart.constants.Constants;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;

public class AuthenticationResponse {
	private String username;

	private String name;

	private String jwtAuthToken;

	private String tokenExpirationTime;

	private Constants.ResponseStatus status_code;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJwtAuthToken() {
		return jwtAuthToken;
	}

	public void setJwtAuthToken(String jwtAuthToken) {
		this.jwtAuthToken = jwtAuthToken;
	}

	public String getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(String tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public Constants.ResponseStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(Constants.ResponseStatus status_code) {
		this.status_code = status_code;
	}

	public AuthenticationResponse(String username, String name, String jwtAuthToken, String tokenExpirationTime,
			ResponseStatus status_code) {
		super();
		this.username = username;
		this.name = name;
		this.jwtAuthToken = jwtAuthToken;
		this.tokenExpirationTime = tokenExpirationTime;
		this.status_code = status_code;
	}

	public AuthenticationResponse() {
		super();
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [username=" + username + ", name=" + name + ", jwtAuthToken=" + jwtAuthToken
				+ ", tokenExpirationTime=" + tokenExpirationTime + ", status_code=" + status_code + "]";
	}

}

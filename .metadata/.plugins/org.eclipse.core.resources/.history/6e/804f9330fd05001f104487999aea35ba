package com.service.inventory.dto;

public class AuthenticationResponse {
	private String username;

	private String password;

	private String jwtAuthToken;

	private long serverCurrentTime;

	private long tokenExpirationTime;

	public AuthenticationResponse(String username, String password, String jwtAuthToken, long serverCurrentTime,
			long tokenExpirationTime) {
		super();
		this.username = username;
		this.password = password;
		this.jwtAuthToken = jwtAuthToken;
		this.serverCurrentTime = serverCurrentTime;
		this.tokenExpirationTime = tokenExpirationTime;
	}

	public AuthenticationResponse() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getJwtAuthToken() {
		return jwtAuthToken;
	}

	public void setJwtAuthToken(String jwtAuthToken) {
		this.jwtAuthToken = jwtAuthToken;
	}

	public long getServerCurrentTime() {
		return serverCurrentTime;
	}

	public void setServerCurrentTime(long serverCurrentTime) {
		this.serverCurrentTime = serverCurrentTime;
	}

	public long getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(long tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [username=" + username + ", password=" + password 
				+ ", jwtAuthToken=" + jwtAuthToken + ", serverCurrentTime=" + serverCurrentTime
				+ ", tokenExpirationTime=" + tokenExpirationTime + "]";
	}
}

package com.cts.shopping.cart.response;

public class AuthenticationResponse {
	private String username;

	private String password;

	private String jwtAuthToken;

	private String serverCurrentTime;

	private String tokenExpirationTime;

	public AuthenticationResponse(String username, String password, String jwtAuthToken, String serverCurrentTime,
			String tokenExpirationTime) {
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

	public String getServerCurrentTime() {
		return serverCurrentTime;
	}

	public void setServerCurrentTime(String serverCurrentTime) {
		this.serverCurrentTime = serverCurrentTime;
	}

	public String getTokenExpirationTime() {
		return tokenExpirationTime;
	}

	public void setTokenExpirationTime(String tokenExpirationTime) {
		this.tokenExpirationTime = tokenExpirationTime;
	}

	@Override
	public String toString() {
		return "AuthenticationResponse [username=" + username + ", password=" + password + ", jwtAuthToken="
				+ jwtAuthToken + ", serverCurrentTime=" + serverCurrentTime + ", tokenExpirationTime="
				+ tokenExpirationTime + "]";
	}
}

package com.cts.shopping.cart.response;

public class UserInfo {
	private long id;
	private String username;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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

	public UserInfo(long id, String username, String name) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
	}

}

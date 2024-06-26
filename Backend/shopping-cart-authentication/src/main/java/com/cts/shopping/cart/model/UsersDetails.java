package com.cts.shopping.cart.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "USER_DETAIlS")
public class UsersDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String username;
	private String password;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public UsersDetails(String username, String name, String password) {
		super();
		this.username = username;
		this.name = name;
		this.password = password;
	}

	public UsersDetails() {
		super();
	}

	@Override
	public String toString() {
		return "UsersDetails [id=" + id + ", name=" + name + ", username=" + username + ", password=" + password + "]";
	}

}

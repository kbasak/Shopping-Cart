package com.cts.shopping.cart.service;

import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.response.UserInfo;

public interface AuthService {
	public UsersDetails registerUser(UsersDetails user) throws Exception;

	public boolean existByUsername(String username);

	public UserInfo fetchUserInfo(String username);
}

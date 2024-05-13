package com.cts.shopping.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.clients.AuthClient;
import com.cts.shopping.cart.response.ValidationResponse;

@Service
public class AuthService {

	@Autowired
	private AuthClient authClient;

	public boolean checkValidationStatus(String token) {
		ValidationResponse validationStatus = authClient.validatingAuthorizationToken(token).getBody();
		return validationStatus.isValidStatus();
	}
}

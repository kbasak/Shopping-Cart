package com.cts.shopping.cart.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.clients.AuthClient;
import com.cts.shopping.cart.clients.UserClient;
import com.cts.shopping.cart.response.UserInfo;
import com.cts.shopping.cart.response.ValidationResponse;

@Service
public class ClientService {

	@Autowired
	private AuthClient authClient;

	@Autowired
	private UserClient userClient;

	private Logger logger = LoggerFactory.getLogger(ClientService.class);

	public ValidationResponse checkValidationStatus(String token) {
		try {
			ValidationResponse validationStatus = authClient.validatingAuthorizationToken(token).getBody();
			System.out.println(validationStatus);
			return validationStatus;
		} catch (Exception e) {
			logger.info("Exception in feing service: " + e.toString());
			logger.info("Exception in feing service: " + e.getClass());
			throw e;
		}
	}

	public UserInfo fetchUserInfo(String username) {
		try {
			UserInfo userInfo = userClient.fetchUserInfo(username).getBody();
			logger.info("User Information: " + userInfo);
			return userInfo;
		} catch (Exception e) {
			logger.info("Exception in feing service: " + e.toString());
			logger.info("Exception in feing service: " + e.getClass());
			throw e;
		}
	}
}

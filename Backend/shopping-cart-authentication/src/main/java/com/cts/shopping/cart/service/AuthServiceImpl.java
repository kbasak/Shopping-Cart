package com.cts.shopping.cart.service;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.exception.UserAlreadyExistException;
import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;
import com.cts.shopping.cart.response.UserInfo;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

	@Autowired
	private UsersRepo userRepo;

	private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

	@Override
	public UsersDetails registerUser(UsersDetails user) throws Exception {
		logger.info("Start authService register method");
		if (this.userRepo.existsByUsername(user.getUsername())) {
			logger.info("Username already found in DB");
			throw new UserAlreadyExistException("User Already Exist: " + user.getUsername());
		} else {
			logger.info("User saving db DB");
			logger.info("End authService register method");
			return userRepo.save(user);
		}
	}

	@Override
	public boolean existByUsername(String username) {
		return userRepo.existsByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		UsersDetails usersdetails = userRepo.findByUsername(username);

		if (usersdetails == null) {
			throw new UsernameNotFoundException("User not found for email: " + username);
		}
		return new User(usersdetails.getUsername(), usersdetails.getPassword(), new ArrayList<>());
	}

	@Override
	public UserInfo fetchUserInfo(String username) {
		UsersDetails usersdetails = userRepo.findByUsername(username);
		UserInfo userInfo = new UserInfo(usersdetails.getId(), usersdetails.getUsername(), usersdetails.getName());
		return userInfo;
	}

}

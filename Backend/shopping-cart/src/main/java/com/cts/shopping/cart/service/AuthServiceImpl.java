package com.cts.shopping.cart.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.exception.UserAlreadyExistException;
import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;

@Service
public class AuthServiceImpl implements AuthService, UserDetailsService {

	@Autowired
	private UsersRepo userRepo;

	@Override
	public UsersDetails registerUser(UsersDetails user) throws Exception {
		if (this.userRepo.existsByUsername(user.getUsername())) {
			throw new UserAlreadyExistException("User Already Exist: " + user.getUsername());
		} else {
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

}

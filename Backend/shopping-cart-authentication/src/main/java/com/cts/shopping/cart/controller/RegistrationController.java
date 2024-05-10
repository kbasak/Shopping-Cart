package com.cts.shopping.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.request.RegistrationRequest;
import com.cts.shopping.cart.response.RegistrationResponse;
import com.cts.shopping.cart.service.AuthService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RegistrationController {

	@Autowired
	private AuthService authService;

	@Autowired
	private PasswordEncoder encoder;

	private Logger logger = LoggerFactory.getLogger(RegistrationController.class);

	@PostMapping("/register")
	public ResponseEntity<Object> registerUser(@RequestBody @Valid RegistrationRequest req) throws Exception {

		logger.info("Start Registratin Controller");

		UsersDetails usersDetails = new UsersDetails(req.getUsername().toLowerCase(), req.getName(),
				encoder.encode(req.getPassword()));

		logger.info("User mapped in model class");
		logger.info("Calling Service class");

		usersDetails = authService.registerUser(usersDetails);

		logger.info("User saved in DB & returned user: " + usersDetails);
		logger.debug("{ }" + usersDetails);

		return new ResponseEntity<Object>(new RegistrationResponse(req.getUsername(), ResponseStatus.Success),
				HttpStatus.OK);
	}

}

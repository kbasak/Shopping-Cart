package com.cts.shopping.cart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.config.JwtHelper;
import com.cts.shopping.cart.config.TimeFormatter;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.exception.UserNotFoundException;
import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;
import com.cts.shopping.cart.request.LoginRequest;
import com.cts.shopping.cart.response.AuthenticationFailure;
import com.cts.shopping.cart.response.AuthenticationResponse;
import com.cts.shopping.cart.response.ValidationResponse;
import com.cts.shopping.cart.service.AuthService;
import com.cts.shopping.cart.service.AuthServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private AuthService authService;

	private ValidationResponse validationResponse = new ValidationResponse();

	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private TimeFormatter timeFormatter;

	@PostMapping(path = "/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody LoginRequest loginDTO)
			throws UserNotFoundException {

		boolean b = this.authService.existByUsername(loginDTO.getUsername().toLowerCase());
		if (!b) {
			logger.info("Username not found in DB");
			throw new UserNotFoundException("User Not Found : " + loginDTO.getUsername());
		} else {
			logger.info("BEGIN - [login(customerLoginCredentials)]");
			final UserDetails userDetails = authServiceImpl.loadUserByUsername(loginDTO.getUsername().toLowerCase());
			logger.debug("{}", userDetails);
			UsersDetails r = userRepo.findByUsername(loginDTO.getUsername().toLowerCase());
			String username = r.getUsername();
			String name = r.getName();

			// if (userDetails.getPassword().equals(users.getPassword())) {
			if (passwordEncoder.matches(loginDTO.getPassword(), userDetails.getPassword())) {
				logger.info("Password matched - [login(customerLoginCredentials)]");
				return new ResponseEntity<>(
						new AuthenticationResponse(username, name, jwtHelper.generateToken(userDetails),
								timeFormatter.format(jwtHelper.getExpirationTime()), ResponseStatus.Success),
						HttpStatus.OK);
			}
			logger.info("Password Not matched - [login(customerLoginCredentials)]");
			return new ResponseEntity<>(new AuthenticationFailure(username, "Invalid Password", ResponseStatus.Failure),
					HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> validatingAuthorizationToken(@RequestHeader(name = "Authorization") String tokenDup) throws ExpiredJwtException{
		String token = tokenDup.substring(7);
		try {
			UserDetails user = authServiceImpl.loadUserByUsername(jwtHelper.getUsernameFromToken(token));
			logger.info("Fetching Username from Token: " + user.getUsername());
			logger.info("BEGIN - [validatingAuthorizationToken(JWT-token)]");
			if (Boolean.TRUE.equals(jwtHelper.validateToken(token, user))) {
				logger.debug("Token matched is Valid");
				logger.info("Token matched is Valid");
				logger.info("END - validate()");
				validationResponse.setValidStatus(true);
				return new ResponseEntity<>(validationResponse, HttpStatus.OK);
			} else {
				throw new Exception("Invalid token");
			}
		} catch (Exception e) {
			logger.debug("Invalid token - Bad Credentials Exception");
			logger.info("END Exception - validatingAuthorizationToken()");
			validationResponse.setValidStatus(false);
			return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
		}

	}
}

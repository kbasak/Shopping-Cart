package com.cts.shopping.cart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.config.JwtUtil;
import com.cts.shopping.cart.config.TimeFormatter;
import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.exception.UserNotFoundException;
import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;
import com.cts.shopping.cart.request.RegistrationRequest;
import com.cts.shopping.cart.response.AuthenticationFailure;
import com.cts.shopping.cart.response.AuthenticationResponse;
import com.cts.shopping.cart.response.ValidationResponse;
import com.cts.shopping.cart.service.AuthService;
import com.cts.shopping.cart.service.AuthServiceImpl;

@RestController
public class AuthController {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthServiceImpl authServiceImpl;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private UsersRepo userRepo;

	@Autowired
	private AuthService authService;

	private ValidationResponse validationResponse = new ValidationResponse();

	@Autowired
	private TimeFormatter timeFormatter;

	@PostMapping("/login")
	public ResponseEntity<Object> createAuthorizationToken(@RequestBody RegistrationRequest loginDTO)
			throws UserNotFoundException {

		boolean b = this.authService.existByUsername(loginDTO.getUsername());
		if (!b) {
			throw new UserNotFoundException("User Not Found : " + loginDTO.getUsername());
		} else {

			UsersDetails users = new UsersDetails();
			users.setUsername(loginDTO.getUsername());
			users.setPassword(loginDTO.getPassword());

			final UserDetails userDetails = authServiceImpl.loadUserByUsername(users.getUsername());

			UsersDetails r = userRepo.findByUsername(users.getUsername());
			String username = r.getUsername();
			String password = r.getPassword();

			// if (userDetails.getPassword().equals(users.getPassword())) {
			if (passwordEncoder.matches(users.getPassword(), userDetails.getPassword())) {
				return new ResponseEntity<>(new AuthenticationResponse(username, password,
						jwtTokenUtil.generateToken(userDetails), timeFormatter.format(jwtTokenUtil.getCurrentTime()),
						timeFormatter.format(jwtTokenUtil.getExpirationTime())), HttpStatus.OK);
			}

			return new ResponseEntity<>(new AuthenticationFailure(username, "Invalid Password", ResponseStatus.Failure),
					HttpStatus.FORBIDDEN);
		}
	}

	@GetMapping(path = "/validate", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> validatingAuthorizationToken(@RequestHeader(name = "Authorization") String tokenDup) {
		String token = tokenDup.substring(7);
		try {
			UserDetails user = authServiceImpl.loadUserByUsername(jwtTokenUtil.extractUsername(token));
			System.out.println(user);
			if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(token, user))) {
				validationResponse.setValidStatus(true);
				return new ResponseEntity<>(validationResponse, HttpStatus.OK);
			} else {
				throw new Exception("Invalid token");
			}
		} catch (Exception e) {
			validationResponse.setValidStatus(false);
			return new ResponseEntity<>(validationResponse, HttpStatus.BAD_REQUEST);
		}

	}
}

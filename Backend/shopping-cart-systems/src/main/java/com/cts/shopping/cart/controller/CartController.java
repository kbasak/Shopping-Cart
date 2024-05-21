package com.cts.shopping.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.model.CartDetails;
import com.cts.shopping.cart.response.CartResponse;
import com.cts.shopping.cart.response.ValidationResponse;
import com.cts.shopping.cart.service.ClientService;
import com.cts.shopping.cart.service.ItemServiceImpl;

import feign.FeignException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class CartController {
	@Autowired
	private ClientService authService;

	@Autowired
	private ItemServiceImpl itemService;

	private Logger logger = LoggerFactory.getLogger(CartController.class);

	@GetMapping("/addtocart/{sku}/{quantity}")
	public ResponseEntity<Object> addItemToCart(@PathVariable String sku, @PathVariable int quantity,
			@RequestHeader(name = "Authorization") String token) {
		try {
			logger.info("Checking JWT Token");
			ValidationResponse validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status.isValidStatus() == true) {
				itemService.getUserCarts(validation_status.getUsername());
				logger.info("Validation Status: " + validation_status.isValidStatus());
				CartDetails cartDetails = itemService.addItemToCart(sku, quantity, validation_status.getUsername());
				logger.info("after calling cart method");
				return new ResponseEntity<>(new CartResponse(cartDetails.getId(), cartDetails.getSku(),
						cartDetails.getQuantity(), cartDetails.getStatus(), ResponseStatus.Success), HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (FeignException e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occurs while adding to cart", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getcartdetails")
	public ResponseEntity<Object> getItemFromCart(@RequestHeader(name = "Authorization") String token) {
		try {
			logger.info("Checking JWT Token");
			ValidationResponse validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status.isValidStatus() == true) {
				List<CartDetails> cartDetails = itemService.getUserCarts(validation_status.getUsername());
				logger.info("Validation Status: " + validation_status.isValidStatus());
				logger.info("after calling cart method: Cart Size: " + cartDetails.size());
				return new ResponseEntity<>(cartDetails, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (FeignException e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occurs while fetching carts", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/checkoutcart")
	public ResponseEntity<Object> checkOutFromCart(@RequestHeader(name = "Authorization") String token,
			@RequestParam(required = false) List<Long> id) {
		try {
			logger.info("Checking JWT Token");
			ValidationResponse validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status.isValidStatus() == true) {
				List<String> checkOutResponse = itemService.checkOutCarts(id, validation_status.getUsername());
				logger.info("Validation Status: " + validation_status.isValidStatus());
				logger.info("after calling cart method: Cart Size: ");
				return new ResponseEntity<>(checkOutResponse, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (FeignException e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occurs while fetching carts", HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/deletecart")
	public ResponseEntity<Object> deleteCart(@RequestHeader(name = "Authorization") String token,
			@RequestParam(required = false) List<Long> id) {
		try {
			logger.info("Checking JWT Token");
			ValidationResponse validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status.isValidStatus() == true) {
				List<String> checkOutResponse = itemService.deleteFromCart(id, validation_status.getUsername());
				logger.info("Validation Status: " + validation_status.isValidStatus());
				logger.info("after calling cart method: Cart Size: ");
				return new ResponseEntity<>(checkOutResponse, HttpStatus.OK);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} catch (FeignException e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occurs while fetching carts", HttpStatus.BAD_REQUEST);
		}
	}
}

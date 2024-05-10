package com.cts.shopping.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.model.ItemDetails;
import com.cts.shopping.cart.response.ValidationResponse;
import com.cts.shopping.cart.service.AuthService;
import com.cts.shopping.cart.service.ItemService;

@RestController
public class ItemController {

	@Autowired
	private AuthService authService;

	@Autowired
	private ItemService itemService;

	private Logger logger = LoggerFactory.getLogger(ItemController.class);

	@GetMapping(path = "/loadpurchaseitems")
	public ResponseEntity<Object> getAllItems(@RequestHeader(name = "Authorization") String token) {
		try {
			logger.info("Checking JWT Token");
			boolean validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status == true) {
				List<ItemDetails> listOfItems = itemService.getAllItems();
				return new ResponseEntity<>(listOfItems, HttpStatus.OK);
			}
			return new ResponseEntity<>(new ValidationResponse(false), HttpStatus.BAD_REQUEST);

		} catch (Exception e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
}
package com.cts.shopping.cart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.model.ItemDetails;
import com.cts.shopping.cart.response.FetchItemResponse;
import com.cts.shopping.cart.response.ValidationResponse;
import com.cts.shopping.cart.service.ClientService;
import com.cts.shopping.cart.service.ItemService;

import feign.FeignException;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ItemController {

	@Autowired
	private ClientService authService;

	@Autowired
	private ItemService itemService;

	private Logger logger = LoggerFactory.getLogger(ItemController.class);

	@GetMapping(path = "/loadpurchaseitems")
	public ResponseEntity<Object> getAllItems(@RequestHeader(name = "Authorization") String token) {
		try {
			logger.info("Checking JWT Token");
			ValidationResponse validation_status = authService.checkValidationStatus(token);
			logger.info("Completed Checking JWT Token: " + validation_status);
			if (validation_status.isValidStatus() == true) {
				List<ItemDetails> listOfItems = itemService.getAllItems();
				return new ResponseEntity<>(
						new FetchItemResponse(validation_status.getUsername(), listOfItems, ResponseStatus.Success),
						HttpStatus.OK);
			}
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure), HttpStatus.BAD_REQUEST);

		} catch (FeignException e) {
			return new ResponseEntity<>(new ValidationResponse(false, ResponseStatus.Failure),
					HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<>("Exception occurs while fetching items", HttpStatus.NOT_FOUND);
		}

	}
}

package com.cts.shopping.cart.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
	@PostMapping("/addtocart")
	public ResponseEntity<Object> addItemToCart() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

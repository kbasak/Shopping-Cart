package com.cts.shopping.cart.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cts.shopping.cart.response.UserInfo;

@FeignClient(name = "UserService-ms", url = "http://localhost:9090")
public interface UserClient {
	@GetMapping(path = "/getuserinfo/{username}")
	public ResponseEntity<UserInfo> fetchUserInfo(@PathVariable String username);
}
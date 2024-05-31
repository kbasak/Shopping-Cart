package com.cts.shopping.cart.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;
import com.cts.shopping.cart.response.UserInfo;
import com.cts.shopping.cart.service.AuthServiceImpl;

@SpringBootTest
public class AuthServiceTest {

	@InjectMocks
	AuthServiceImpl authServiceImpl;

	@Mock
	UsersRepo usersRepo;

	@Test
	public void registerUser() throws Exception {
		UsersDetails usersDetails = new UsersDetails("someone@gmail.com", "Someone", "Something@123");
		Mockito.when(usersRepo.save(usersDetails)).thenReturn(usersDetails);

		Mockito.when(usersRepo.existsByUsername("someone@gmail.com")).thenReturn(true);
		Mockito.when(usersRepo.existsByUsername(Mockito.any())).thenReturn(false);

		usersDetails = authServiceImpl.registerUser(usersDetails);

		Assertions.assertNotNull(usersDetails);
		assertThat(authServiceImpl.registerUser(usersDetails)).isEqualTo(usersDetails);
	}

	@Test
	public void loadUserDeatils() throws UsernameNotFoundException {
		UsersDetails usersDetails = new UsersDetails("someone@gmail.com", "Someone", "Something@123");
		when(usersRepo.findByUsername(usersDetails.getUsername())).thenReturn(usersDetails);
		Assertions.assertNotNull(usersDetails);
		when(usersRepo.findByUsername(usersDetails.getUsername())).thenReturn(usersDetails);
		authServiceImpl.loadUserByUsername(usersDetails.getUsername());
	}

	@Test
	public void existByUsername() {
		String username = "abc";
		when(usersRepo.existsByUsername(username)).thenReturn(false);
		authServiceImpl.existByUsername(username);
	}

	@Test
	public void fetchUserInfo() {
		UsersDetails usersDetails1 = new UsersDetails("someone@gmail.com", "Someone", "Something@123");
		String username = "Someone";
		when(usersRepo.findByUsername(username)).thenReturn(usersDetails1);
		UserInfo userinfo = authServiceImpl.fetchUserInfo(username);
		assertNotNull(userinfo);
	}

}

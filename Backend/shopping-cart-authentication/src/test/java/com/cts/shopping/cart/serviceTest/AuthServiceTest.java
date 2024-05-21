package com.cts.shopping.cart.serviceTest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.shopping.cart.model.UsersDetails;
import com.cts.shopping.cart.repository.UsersRepo;
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
	public void existByUsername() {
		UsersDetails usersDetails = new UsersDetails("someone@gmail.com", "Someone", "Something@123");
		Mockito.when(usersRepo.save(usersDetails)).thenReturn(usersDetails);

		Mockito.when(usersRepo.existsByUsername("someone@gmail.com")).thenReturn(true);
		Mockito.when(usersRepo.existsByUsername(Mockito.any())).thenReturn(false);
	}

	@Test
	public void fetchUserInfo() {
		UsersDetails usersDetails1 = new UsersDetails("someone@gmail.com", "Someone", "Something@123");
		String username = "Someone";
		Mockito.when(usersRepo.findByUsername(username)).thenReturn(usersDetails1);
	}

}

package com.cts.shopping.cart.servicetest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.cts.shopping.cart.model.CartDetails;
import com.cts.shopping.cart.model.ItemDetails;
import com.cts.shopping.cart.repository.CartRepo;
import com.cts.shopping.cart.repository.ItemRepo;
import com.cts.shopping.cart.response.UserInfo;
import com.cts.shopping.cart.service.ClientService;
import com.cts.shopping.cart.service.ItemServiceImpl;

@SpringBootTest
public class CartSystemTest {

	@InjectMocks
	ItemServiceImpl itemServiceImpl;

	@Mock
	ItemRepo itemRepo;

	@Mock
	CartRepo cartRepo;

	@Mock
	ClientService clientService;

	List<Long> ids = new ArrayList<Long>();
	String username = "abc";
	String sku = "123456";
	int quantity = 20;
	long userid = 1L;
	CartDetails cartDetails = new CartDetails();

	@Test
	public void getAllItemsTest() {
		List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
		when(itemRepo.findAll()).thenReturn(itemDetails);
		itemServiceImpl.getAllItems();
	}

	@Test
	public void getItemBySkuTest() {
		List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
		when(itemRepo.findBySku(sku)).thenReturn(itemDetails);
		itemServiceImpl.getAllItems();
	}

	@Test
	public void getUserCartsTest() {
		List<CartDetails> cartDetails = new ArrayList<CartDetails>();
		String username = "kausik";
		UserInfo userinfo = new UserInfo(1L, "abc", "abc");
		when(clientService.fetchUserInfo(username)).thenReturn(userinfo);
		long userId = userinfo.getId();
		when(cartRepo.findByUserid(userId)).thenReturn(cartDetails);
		itemServiceImpl.getUserCarts(username);
	}

	@SuppressWarnings("static-access")
	@Test
	public void deleteFromCart_Test() {
		ids.add(13L);
		Optional<CartDetails> cartDetails = Optional.of(new CartDetails());
		cartDetails.of(cartDetails);
		when(cartRepo.findById(ids.get(0))).thenReturn(cartDetails);
		itemServiceImpl.deleteFromCart(ids, username);
	}

	@Test
	public void addItemToCartTest() {
		UserInfo userinfo = new UserInfo(1L, "abc", "abc");
		when(clientService.fetchUserInfo(username)).thenReturn(userinfo);

		List<ItemDetails> itemDetails = new ArrayList<ItemDetails>();
		when(itemRepo.findBySku(sku)).thenReturn(itemDetails);

		CartDetails cartDetails = new CartDetails(1L, 1L, "123", 12, 20.0, "image", "A", "product", "abc", 12);
		List<CartDetails> cartDetailsList = new ArrayList<CartDetails>();
		when(cartRepo.save(cartDetails)).thenReturn(cartDetails);
		when(cartRepo.findBySkuAndUserid(sku, userid)).thenReturn(cartDetailsList);
	}

}

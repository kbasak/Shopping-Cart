package com.cts.shopping.cart.service;

import java.util.List;

import com.cts.shopping.cart.model.CartDetails;
import com.cts.shopping.cart.model.ItemDetails;

public interface ItemService {
	public List<ItemDetails> getAllItems();

	public List<ItemDetails> getItemBySku(String sku);

	public CartDetails addItemToCart(String sku, int quantity, String username);

	public List<CartDetails> getUserCarts(String username);

	public List<String> checkOutCarts(List<Long> id, String username);
	
	public List<String> deleteFromCart(List<Long> ids, String username);
}

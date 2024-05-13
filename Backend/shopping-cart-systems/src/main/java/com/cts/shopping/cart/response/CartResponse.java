package com.cts.shopping.cart.response;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;

public class CartResponse {
	private long id;
	private String sku;
	private int quantity;
	private String status;
	private ResponseStatus addToCartStatus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ResponseStatus getAddToCartStatus() {
		return addToCartStatus;
	}

	public void setAddToCartStatus(ResponseStatus addToCartStatus) {
		this.addToCartStatus = addToCartStatus;
	}

	public CartResponse(long id, String sku, int quantity, String status, ResponseStatus addToCartStatus) {
		super();
		this.id = id;
		this.sku = sku;
		this.quantity = quantity;
		this.status = status;
		this.addToCartStatus = addToCartStatus;
	}

}

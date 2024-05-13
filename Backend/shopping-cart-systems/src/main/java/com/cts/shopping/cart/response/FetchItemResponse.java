package com.cts.shopping.cart.response;

import java.util.List;

import com.cts.shopping.cart.constants.Constants.ResponseStatus;
import com.cts.shopping.cart.model.ItemDetails;

public class FetchItemResponse {
	private String username;
	private List<ItemDetails> listOfItems;
	private ResponseStatus status_code;

	public List<ItemDetails> getListOfItems() {
		return listOfItems;
	}

	public void setListOfItems(List<ItemDetails> listOfItems) {
		this.listOfItems = listOfItems;
	}

	public ResponseStatus getStatus_code() {
		return status_code;
	}

	public void setStatus_code(ResponseStatus status_code) {
		this.status_code = status_code;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public FetchItemResponse(String username, List<ItemDetails> listOfItems, ResponseStatus status_code) {
		super();
		this.username = username;
		this.listOfItems = listOfItems;
		this.status_code = status_code;
	}

	public FetchItemResponse() {
		super();
	}
}

package com.cts.shopping.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.shopping.cart.model.ItemDetails;
import com.cts.shopping.cart.repository.ItemRepo;

@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemRepo itemRepo;

	@Override
	public List<ItemDetails> getAllItems() {
		return itemRepo.findAll();
	}

}

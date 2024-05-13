package com.cts.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.shopping.cart.model.CartDetails;

@Repository
public interface CartRepo extends JpaRepository<CartDetails, Long> {
	List<CartDetails> findBySkuAndUserid(String sku, long userid);

	List<CartDetails> findByUserid(long id);

	List<CartDetails> findBySku(String sku);
}

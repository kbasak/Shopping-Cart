package com.cts.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.shopping.cart.model.UsersDetails;

@Repository
public interface UsersRepo extends JpaRepository<UsersDetails, Long> {
	boolean existsByUsername(String username);

	UsersDetails findByUsername(String username);
}
package com.arifRizal.online_shops.repository;

import com.arifRizal.online_shops.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUserId(Long userId);
}

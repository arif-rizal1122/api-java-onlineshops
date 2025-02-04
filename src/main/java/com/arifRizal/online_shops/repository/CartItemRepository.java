package com.arifRizal.online_shops.repository;

import com.arifRizal.online_shops.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCardId(Long id);
}

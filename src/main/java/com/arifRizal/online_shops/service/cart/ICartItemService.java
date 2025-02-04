package com.arifRizal.online_shops.service.cart;

import com.arifRizal.online_shops.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, int quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateItemQuntity(Long cartId, Long productId, int quantity);

    CartItem getCartItem(Long cartId, Long productId);
}

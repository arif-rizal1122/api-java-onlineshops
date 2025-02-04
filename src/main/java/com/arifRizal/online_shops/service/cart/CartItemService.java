package com.arifRizal.online_shops.service.cart;

import com.arifRizal.online_shops.exception.ResourceNotFoundException;
import com.arifRizal.online_shops.model.Cart;
import com.arifRizal.online_shops.model.CartItem;
import com.arifRizal.online_shops.model.Product;
import com.arifRizal.online_shops.repository.CartItemRepository;
import com.arifRizal.online_shops.repository.CartRepository;
import com.arifRizal.online_shops.service.product.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartItemService implements ICartItemService{
    private final CartItemRepository cartItemRepository;
    private final IProductService productService;
    private final ICartService cartService;
    private final CartRepository cartRepository;

    @Override
    public void addItemToCart(Long cartId, Long productId, int quantity) {
          //1. get the cart
          //2. get the product
          //3. check if the product already in the cart
          //4. if yes, then increase the quantity with the requested quantity
          //5. if no, the initiate a new cartItem entry

        Cart cart = cartService.getCart(cartId);
        Product product = productService.getProductById(productId);

        CartItem cartItem = cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId()
                .equals(productId))
                .findFirst().orElse(new CartItem());
        if (cartItem.getId() == null){
                cartItem.setCart(cart);
                cartItem.setProduct(product);
                cartItem.setQuantity(quantity);
                cartItem.setUnitPrice(product.getPrice());
        }
        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {
        Cart cart = cartService.getCart(cartId);
        CartItem itemRemove = getCartItem(cartId, productId);
        cart.removeItem(itemRemove);
        cartRepository.save(cart);
    }

    @Override
    public void updateItemQuntity(Long cartId, Long productId, int quantity) {
       Cart cart = cartService.getCart(cartId);
       cart.getCartItems()
               .stream()
               .filter(item -> item.getProduct().getId().equals(productId))
               .findFirst()
               .ifPresent(item -> {
                   item.setQuantity(quantity);
                   item.setUnitPrice(item.getProduct().getPrice());
                   item.setTotalPrice();
               });
        BigDecimal totalAmount = cart.getTotalAmount();
        cart.setTotalAmount(totalAmount);
        cartRepository.save(cart);
    }

    @Override
    public CartItem getCartItem(Long cartId, Long productId){
        Cart cart = cartService.getCart(cartId);
        return cart.getCartItems()
                .stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst().orElseThrow(() -> new ResourceNotFoundException("Product Not Found"));
    }

}

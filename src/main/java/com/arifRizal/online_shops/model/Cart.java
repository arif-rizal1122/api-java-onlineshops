package com.arifRizal.online_shops.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Cart {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private BigDecimal totalAmount = BigDecimal.ZERO;

     @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
     private Set<CartItem> cartItems;

     public void addItem(CartItem item){
         this.cartItems.add(item);
         item.setCart(this);
         updateTotalAmount();
     }

     private void updateTotalAmount(){
         this.totalAmount = cartItems.stream()
                 .map(item -> {
                     BigDecimal unitPrice = item.getUnitPrice();
                     if (unitPrice == null){
                         return BigDecimal.ZERO;
                     }
                     return unitPrice.multiply(BigDecimal.valueOf(item.getQuantity()));
                 }).reduce(BigDecimal.ZERO, BigDecimal::add);
     }

     public void removeItem(CartItem item){
         this.cartItems.remove(item);
         item.setCart(null);
         updateTotalAmount();
     }
}

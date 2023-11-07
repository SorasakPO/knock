package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class CartItem {

    @Id
    @GeneratedValue
    private UUID cartItemId;

    @ManyToOne
//    @MapsId("cartId")
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
//    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    // เมธอด getter และ setter สำหรับ cart
    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    // เมธอด getter และ setter สำหรับ product
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    // เมธอด getter และ setter สำหรับ quantity
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart createCart(Member owner) {
        Cart cart = new Cart();
        cart.setOwner(owner);
        return cartRepository.save(cart);
    }

    public Cart getCartByOwner(Member owner) {
        return cartRepository.findByOwner(owner);
    }

    public void addToCart(Cart cart, Product product, int quantity) {
        // ตรวจสอบว่ามีสินค้านี้อยู่ในตะกร้าหรือไม่
        CartItem existingCartItem = findCartItemByProduct(cart, product);

        if (existingCartItem != null) {
            // หากมี, เพิ่มปริมาณ
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
            cartItemRepository.save(existingCartItem);
        } else {
            // หากไม่มี, สร้าง CartItem ใหม่
            CartItem newCartItem = new CartItem();
            newCartItem.setCart(cart);
            newCartItem.setProduct(product);
            newCartItem.setQuantity(quantity);
            cartItemRepository.save(newCartItem);
        }
    }

    private CartItem findCartItemByProduct(Cart cart, Product product) {
        return cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    public void clearCart(Cart cart) {
        cart.getCartItems().clear();
        cartRepository.save(cart);
    }
}

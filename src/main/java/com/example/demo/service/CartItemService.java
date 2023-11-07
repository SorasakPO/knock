package com.example.demo.service;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Product;
import com.example.demo.repository.CartItemRepository;
import com.example.demo.repository.CartRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private CartRepository cartRepository;

    public void saveCartItem(CartItem cartItem) {
        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getAllCartItems() {
        return cartItemRepository.findAll();
    }

    public CartItem getCartItemById(UUID cartItemId) {
        return cartItemRepository.findById(cartItemId).orElse(null);
    }

    public void deleteCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

    public void updateQuantity(UUID cartItemId, int quantityChange) {
        CartItem cartItem = getCartItemById(cartItemId);
        int oldQuantity = cartItem.getQuantity();
        if (cartItem != null) {

            if(quantityChange > 0) {
                int newQuantity = cartItem.getQuantity() + 1;
                cartItem.setQuantity(newQuantity);
                cartItemRepository.save(cartItem);
            }else {
                if (oldQuantity > 1){
                    int newQuantity = cartItem.getQuantity() - 1;
                    cartItem.setQuantity(newQuantity);
                    cartItemRepository.save(cartItem);
                }
            }
        }
    }

    public void updateQuantityFromTagInput(UUID cartItemId, int quantityChange) {
        CartItem cartItem = getCartItemById(cartItemId);
        if (quantityChange > 0){
            cartItem.setQuantity(quantityChange);
            cartItemRepository.save(cartItem);
        }

    }

    public void deleteItemInCartItem(UUID cartItemId) {
        cartItemRepository.deleteById(cartItemId);
    }

}

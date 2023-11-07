package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Member;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String viewCart(Principal principal, Model model) {
        Member owner = memberService.getByUsername(principal.getName());
        Cart cart = cartService.getCartByOwner(owner);
        model.addAttribute("cart", cart);
        return "cart";
    }
}

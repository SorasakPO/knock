package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Member;
import com.example.demo.entity.Product;
import com.example.demo.model.ProductRequest;
import com.example.demo.service.CartService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;


@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MemberService memberService;

    @GetMapping
    public String getAllProducts(Model model) {
        model.addAttribute("products", productService.getAllProduct());
        return "product-all";
    }

    @GetMapping("/add")
    public String getProductForm(Model model) {
        return "product-add";
    }

    @PostMapping("/add")
    public String createProduct(@ModelAttribute ProductRequest product,
                                Model model) {
        productService.createProduct(product);
        return "redirect:/products";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam UUID productId, Principal principal) {
        Member owner = memberService.getByUsername(principal.getName());
        Product product = productService.getProductById(productId);
        Cart cart = cartService.getCartByOwner(owner);
        if (cart == null) {
            cart = cartService.createCart(owner);
        }

        cartService.addToCart(cart, product, 1); // 1 is the default quantity
        return "redirect:/products";
    }


}
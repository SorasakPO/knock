package com.example.demo.controller;

import com.example.demo.entity.Cart;
import com.example.demo.entity.CartItem;
import com.example.demo.entity.Member;
import com.example.demo.model.ProductRequest;
import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/PurchaseOrder")
public class PurchaseOrderController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @PostMapping("/createOrder")
    public String createOrder(Principal principal) {
        // Get the current user
        Member owner = memberService.getByUsername(principal.getName());
        purchaseOrderService.createOrder(owner);

        return "redirect:/products"; // Redirect to product page or wherever you want
    }

}

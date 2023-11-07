package com.example.demo.controller;

import com.example.demo.repository.PurchaseOrderRepository;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Controller
@RequestMapping("/admin/orders")
public class AdminOrderController {

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



    @GetMapping
    public String viewOrder(Model model) {
        model.addAttribute("orders", purchaseOrderService.getAllPurchaseOrder());
        return "order-all";
    }

    @PostMapping("/finish")
    public String finishOrder(@RequestParam UUID orderId) {
//        System.out.println("ADMIN: "+orderId);
        purchaseOrderService.finishOrder(orderId);
        return "redirect:/admin/orders";
    }

    @PostMapping("/editStatus")
    public String editStatusOrder(@RequestParam UUID orderId) {
//        System.out.println("ADMIN: "+orderId);
        purchaseOrderService.editStatusOrder(orderId);
        return "redirect:/admin/orders";
    }

}

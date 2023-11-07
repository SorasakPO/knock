package com.example.demo.controller;


import com.example.demo.entity.CartItem;
import com.example.demo.service.CartItemService;
import com.example.demo.service.CartService;
import com.example.demo.service.MemberService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cartItem")
public class CartItemController {
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private CartService cartService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private ProductService productService;

    // แสดงรายการทั้งหมดของ CartItems
    @GetMapping("/getAll")
    public String getAllCartItems(Model model) {
        List<CartItem> cartItems = cartItemService.getAllCartItems();
        model.addAttribute("cartItems", cartItems);
        return "cartItem-all"; // แสดงข้อมูล cart items ในหน้า all
    }

    //     แสดงรายละเอียดของ CartItem จาก ID
    @GetMapping("/{cartItemId}")
    public String getCartItemById(@PathVariable UUID cartItemId, Model model) {
        CartItem cartItem = cartItemService.getCartItemById(cartItemId);
        model.addAttribute("cartItem", cartItem);
        return "cartItem-detail"; // แสดงรายละเอียดของ cart item
    }

    // ลบ CartItem จาก ID
    @PostMapping("/delete/{cartItemId}")
    public String deleteCartItem(@PathVariable UUID cartItemId) {
        cartItemService.deleteCartItem(cartItemId);
        return "redirect:/cartItem/getAll"; // ส่งกลับไปยังหน้า all หลังจากลบ
    }

    @PostMapping("/updateQuantity")
    public String updateQuantity(@RequestParam UUID cartItemId, @RequestParam int quantityChange, Model model) {
        try {
            cartItemService.updateQuantity(cartItemId, quantityChange);
            return "redirect:/cart";
        } catch (Exception e) {
            return "redirect:/cart";
        }

    }

    @PostMapping("/updateQuantityFromTagInput")
    public String updateQuantityFromTagInput(@RequestParam UUID cartItemId, @RequestParam int quantityChange) {
        try {
            cartItemService.updateQuantityFromTagInput(cartItemId, quantityChange);
            return "redirect:/cart";
        } catch (Exception e) {
            return "redirect:/cart";
        }

    }

    @PostMapping("/deleteItemInCartItem")
    public String deleteItemInCartItem(@RequestParam UUID cartItemId) {
        System.out.println("CONTROLLER");
        cartItemService.deleteItemInCartItem(cartItemId);
        return "redirect:/cart";
    }

}


package com.example.demo.service;

import com.example.demo.comon.Status;
import com.example.demo.entity.*;
import com.example.demo.repository.OrderItemRepository;
import com.example.demo.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    private UUID currentOrderId;

    public void createOrder(Member owner) {

        PurchaseOrder newOrder = new PurchaseOrder();
        newOrder.setOwner(owner);
        newOrder.setTimestamp(LocalDateTime.now());
        newOrder.setStatus(Status.ORDER);
//        purchaseOrderRepository.save(newOrder);
        PurchaseOrder order = purchaseOrderRepository.save(newOrder);
//        System.out.println("ORDER: "+order);
        currentOrderId = order.getOrderId();
//        System.out.println("ID ORDER: "+currentOrderId);

        Cart cart = cartService.getCartByOwner(owner);

        for (CartItem cartItem : cart.getCartItems()) {
//            System.out.println(cartItem.getCartItemId());
            OrderItem orderItem = new OrderItem();
            orderItem.setPurchaseOrder(order);
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setProductQuantity(cartItem.getQuantity());
//            System.out.println("LOOP");
//            System.out.println(order.getOrderId());
//            System.out.println(cartItem.getProduct().getProductId());
//            System.out.println(cartItem.getQuantity());
            orderItemRepository.save(orderItem);
            cartItemService.deleteCartItem(cartItem.getCartItemId());
        }


        cartService.clearCart(cart);
    }


    public List<PurchaseOrder> getAllPurchaseOrder() {
        return purchaseOrderRepository.findAll();
    }

    public void finishOrder(UUID orderId) {
        PurchaseOrder record = purchaseOrderRepository.getReferenceById(orderId);
        record.setStatus(Status.FINISH);
        purchaseOrderRepository.save(record);
    }

    public void editStatusOrder(UUID orderId) {
        PurchaseOrder record = purchaseOrderRepository.getReferenceById(orderId);
        record.setStatus(Status.ORDER);
        purchaseOrderRepository.save(record);
    }

}
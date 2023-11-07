package com.example.demo.entity;

import com.example.demo.comon.Status;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private UUID orderId;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
//    private int productQuantity;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Member owner;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime timestamp;
    private Status status;
}

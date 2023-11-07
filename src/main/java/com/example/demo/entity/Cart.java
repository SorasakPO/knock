package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Cart {

    @Id
    @GeneratedValue
    private UUID cartId;

    @ManyToOne
    @JoinColumn(name = "owner_id") // เพิ่ม annotation นี้
    private Member owner; // เพื่อระบุเจ้าของตะกร้า

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>();

}

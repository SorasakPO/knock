package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue
    private UUID productId;

    private String productName;
    private String productDesc;
    private double productPrice;
    private int productStock;
    private String productImageUrl;
    private String category;

}
package com.example.demo.model;

import lombok.Data;

@Data
public class ProductRequest {

    private String productName;

    private String productDesc;

    private double productPrice;

    private int productStock;

    private String productImageUrl;

    private String category;

}
package com.geekbrains.springweb.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private float productPrice;
    private int quantity;
    private float price;

    public OrderItemDto(ProductDto productDto) {
        this.productId = productDto.getId() ;
        this.productPrice = productDto.getPrice();
        this.productTitle = productDto.getTitle();
        this.quantity = 1;
        this.price = productDto.getPrice();
    }

    public void changeQuantity (int delta) {
        this.quantity += delta;
        this.price = this.quantity * this.productPrice;
    }
}

package com.geekbrains.springweb.dto;

import com.geekbrains.springweb.model.Product;

public class ProductDto {
    private Long id;
    private Float cost;
    private String name;

    public void setId(Long id) {
        this.id = id;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Float getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public ProductDto(Product product) {
        this.cost = product.getCost();
        this.name = product.getName();
        this.id = product.getId();
    }

    public ProductDto() {
    }
}


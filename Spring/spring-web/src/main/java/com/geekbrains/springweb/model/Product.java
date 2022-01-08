package com.geekbrains.springweb.model;

import com.geekbrains.springweb.dto.ProductDto;

import javax.persistence.*;

@Entity
@Table(name = "products")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cost")
    private Float cost;

    @Column(name = "name")
    private String name;

    public Product() {
    }

    public Product(ProductDto productDto) {
        this.id = productDto.getId();
        this.name = productDto.getName();
        this.cost  = productDto.getCost();
    }

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


}


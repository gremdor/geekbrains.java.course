package com.geekbrains.springweb.model;

public class Product {
    private Long id;
    private Float cost;
    private String name;


    public Product() {
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

    public Product(Long id, Float cost, String name) {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }


}


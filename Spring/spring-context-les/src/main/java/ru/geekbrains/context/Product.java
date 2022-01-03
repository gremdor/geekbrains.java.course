package ru.geekbrains.context;

public class Product {
    private Long id;
    private Float cost;
    private String name;


    public Product() {
    }

    public Product(Long id, Float cost, String name) {
        this.id = id;
        this.cost = cost;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", cost=" + cost +
                ", name='" + name + '\'';
    }
}


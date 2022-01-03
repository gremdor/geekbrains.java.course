package ru.geekbrains.context;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void addProduct (Product product) {
        items.add(product);
    }

    public void removeProductById (long id) {
        items.removeIf(o -> o.getId().equals(id));
    }

    @Override
    public String toString() {
        return "Cart items: {" + items.toString() + '}';
    }
}

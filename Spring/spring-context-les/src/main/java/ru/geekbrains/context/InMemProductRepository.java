package ru.geekbrains.context;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Primary
public class InMemProductRepository implements ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>(List.of(
                new Product(1L, 1.13f, "Milk"),
                new Product(2L, 7.00f, "Butter"),
                new Product(3L, 0.1f, "Bread"),
                new Product(4L, 0.84f, "Coffee"),
                new Product(5L, 4.07f, "Honey")
        ));
    }

    @Override
    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    @Override
    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().orElseThrow(() -> new RuntimeException("Product not found"));
    }

}

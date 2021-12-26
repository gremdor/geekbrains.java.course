package com.geekbrains.springweb.services;

import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public void changeCost(Long productId, Float delta) {
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new com.geekbrains.spring.web.exceptions.ResourceNotFoundException("Unable to change Product's score. Product not found, id: " + productId));
        product.setCost(product.getCost() + delta);
    }

    public List<Product> findByCostBetween(Float min, Float max) {
        return productRepository.findAllByCostBetween(min, max);
    }
}

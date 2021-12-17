package com.geekbrains.springweb.services;

import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
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

//    @Transactional
//    public void changeScore(Long ProductId, Integer delta) {
//        Product Product = ProductRepository.findById(ProductId).orElseThrow(() -> new ResourceNotFoundException("Unable to change Product's score. Product not found, id: " + ProductId));
//        Product.setScore(Product.getScore() + delta);
//    }
//
//    public List<Product> findByScoreBetween(Integer min, Integer max) {
//        return ProductRepository.findAllByScoreBetween(min, max);
//    }
}

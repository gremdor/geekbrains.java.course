package com.geekbrains.springweb.services;

import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
import com.geekbrains.springweb.repositories.specifications.ProductSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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

    public Page<Product> find (Float minCost, Float maxCost, String partName, Integer page, Integer size) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterThanOrEqualTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualTo(maxCost));
        }
        if (partName != null) {
            spec = spec.and(ProductSpecifications.nameLike(partName));
        }
        return  productRepository.findAll(spec, PageRequest.of(page-1, size));
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

//    public List<Product> findByCostBetween(Float min, Float max) {
//        return productRepository.findAllByCostBetween(min, max);
//    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update (Product product) {
        Product p = productRepository.findById(product.getId()).orElseThrow(() ->
                new com.geekbrains.spring.web.exceptions.ResourceNotFoundException("Unable to update. Product not found, id: " + product.getId()));
        p.setCost(product.getCost());
        p.setName(product.getName());
        return p;
    }

    public Long count (Float minCost, Float maxCost, String partName) {
        Specification<Product> spec = Specification.where(null);
        if (minCost != null) {
            spec = spec.and(ProductSpecifications.costGreaterThanOrEqualTo(minCost));
        }
        if (maxCost != null) {
            spec = spec.and(ProductSpecifications.costLessThanOrEqualTo(maxCost));
        }
        if (partName != null) {
            spec = spec.and(ProductSpecifications.nameLike(partName));
        }

        return productRepository.count(spec);
    }
}

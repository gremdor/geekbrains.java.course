package com.geekbrains.springweb.services;

import com.geekbrains.springweb.exceptions.ResourceNotFoundException;
import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
import com.geekbrains.springweb.repositories.specifications.ProductSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Page<Product> find (Float minPrice, Float maxPrice, String partTitle, Integer page, Integer size) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecifications.titleLike(partTitle));
        }
        return  productRepository.findAll(spec, PageRequest.of(page-1, size));
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public Product save (Product product) {
        return productRepository.save(product);
    }

    @Transactional
    public Product update (Product product) {
        Product p = productRepository.findById(product.getId()).orElseThrow(() ->
                new ResourceNotFoundException("Unable to update. Product not found, id: " + product.getId()));
        p.setPrice(product.getPrice());
        p.setTitle(product.getTitle());
        return p;
    }

    public Long count (Float minPrice, Float maxPrice, String partTitle) {
        Specification<Product> spec = Specification.where(null);
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.priceGreaterThanOrEqualTo(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.priceLessThanOrEqualTo(maxPrice));
        }
        if (partTitle != null) {
            spec = spec.and(ProductSpecifications.titleLike(partTitle));
        }

        return productRepository.count(spec);
    }
}

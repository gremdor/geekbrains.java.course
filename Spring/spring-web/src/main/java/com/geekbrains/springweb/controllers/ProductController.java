package com.geekbrains.springweb.controllers;

import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "min_cost", required = false) Float minCost,
            @RequestParam(name = "max_cost", required = false) Float maxCost,
            @RequestParam(name = "name_part", required = false) String partName
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minCost, maxCost, partName, page, size).
                map(p -> new ProductDto(p));
    }

    @GetMapping("/cnt")
    public Long getCount(
            @RequestParam(name = "min_cost", required = false) Float minCost,
            @RequestParam(name = "max_cost", required = false) Float maxCost,
            @RequestParam(name = "name_part", required = false) String partName
    ) {
        return productService.count(minCost, maxCost, partName);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product =  productService.findById(id).orElseThrow(() ->
                new com.geekbrains.spring.web.exceptions.ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        product.setId(null);
        product = productService.save(product);
        return new ProductDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        Product product = new Product(productDto);
        product = productService.update(product);
        return new ProductDto(product);
    }

}

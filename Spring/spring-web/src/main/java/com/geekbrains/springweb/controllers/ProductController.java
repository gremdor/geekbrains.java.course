package com.geekbrains.springweb.controllers;

import com.geekbrains.springweb.converters.ProductConverter;
import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.exceptions.ResourceNotFoundException;
import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.services.ProductService;
import com.geekbrains.springweb.validators.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor

public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final ProductValidator productValidator;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam(name = "page", defaultValue = "1") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size,
            @RequestParam(name = "min_price", required = false) Float minPrice,
            @RequestParam(name = "max_price", required = false) Float maxPrice,
            @RequestParam(name = "title_part", required = false) String partTitle
    ) {
        if (page < 1) {
            page = 1;
        }
        return productService.find(minPrice, maxPrice, partTitle, page, size).map(
                p -> productConverter.entityToDto(p)
        );
    }

    @GetMapping("/cnt")
    public Long getCount(
            @RequestParam(name = "min_price", required = false) Float minPrice,
            @RequestParam(name = "max_price", required = false) Float maxPrice,
            @RequestParam(name = "title_part", required = false) String partTitle
    ) {
        return productService.count(minPrice, maxPrice, partTitle);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found, id: " + id));
        return productConverter.entityToDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto saveNewProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.save(product);
        return productConverter.entityToDto(product);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        productValidator.validate(productDto);
        Product product = productConverter.dtoToEntity(productDto);
        product = productService.update(product);
        return productConverter.entityToDto(product);
    }
}

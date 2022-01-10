package com.geekbrains.springweb.converters;

import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {
    public Product dtoToEntity(ProductDto productDto) {
        return new Product(productDto.getId(), productDto.getPrice(), productDto.getTitle());
    }

    public ProductDto entityToDto(Product product) {
        return new ProductDto(product.getId(), product.getPrice(), product.getTitle());
    }

}

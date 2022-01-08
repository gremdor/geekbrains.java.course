package com.geekbrains.springweb.validators;

import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductValidator {
    public void validate(ProductDto productDto) {
        List<String> errors = new ArrayList<>();
        if (productDto.getPrice() <= 0) {
            errors.add("Incorrect product price (negative value is not allowed)");
        }
        if (productDto.getTitle().isBlank()) {
            errors.add("Incorrect product title (empty value is not allowed)");
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

}

package com.geekbrains.springweb.services;

import com.geekbrains.springweb.converters.ProductConverter;
import com.geekbrains.springweb.dto.Cart;
import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.exceptions.ResourceNotFoundException;
import com.geekbrains.springweb.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private final ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        this.cart = new Cart();
    }

    public Cart getCart () {return this.cart;}

    public void addProductByIdToCart(Long productId) {
        if (!cart.addProduct(productId)) {
            Product product = productService.findById(productId).orElseThrow(() ->
                    new ResourceNotFoundException("Product not found, id: " + productId));
            ProductConverter converter = new ProductConverter();
            cart.addProduct(converter.entityToDto(product));
        }
    }
    public void removeProductFromCart (Long productId) {
        cart.removeProduct(productId);
    }
}

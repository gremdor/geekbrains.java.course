package com.geekbrains.springweb.controllers;

import com.geekbrains.springweb.dto.Cart;
import com.geekbrains.springweb.dto.ProductDto;
import com.geekbrains.springweb.exceptions.ResourceNotFoundException;
import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor

public class CartController {
    private final CartService cartService;

    @GetMapping
    public Cart getCart() {
        return cartService.getCart();
    }

    @GetMapping("/add/{id}")
    public void addProductById(@PathVariable Long id) {
        cartService.addProductByIdToCart(id);
    }

    @DeleteMapping("/{id}")
    public void removeById(@PathVariable Long id) {
        cartService.removeProductFromCart(id);
    }

}

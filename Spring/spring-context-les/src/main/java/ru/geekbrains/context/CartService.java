package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@Component
public class CartService {
    private ProductService productService;
    private Cart cart;

    @PostConstruct
    public void init() {
        cart = new Cart();
    }

    public ProductService getProductService() {
        return productService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    public void addProductByIdToCart(Long productId) {
        cart.addProduct(productService.getProductById(productId));
        System.out.println("Product added to cart: " + productService.getTitleById(productId));
    }

    public void removeProductByIdFromCart (Long productId) {
        cart.removeProductById(productId);
        System.out.println("Product removed from cart: " + productService.getTitleById(productId));
    }

    public void printCart () {
        System.out.println(cart.toString());
    }
}
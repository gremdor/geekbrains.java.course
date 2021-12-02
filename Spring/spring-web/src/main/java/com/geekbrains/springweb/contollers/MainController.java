package com.geekbrains.springweb.contollers;

import com.geekbrains.springweb.model.Product;
import com.geekbrains.springweb.repositories.ProductRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {
    private ProductRepository productRepository;

    public MainController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public  String showProductPage (Model model){
        model.addAttribute("products", productRepository.getAllProducts());
        return "products_page";
    }

    @GetMapping("/products/{id}")
    public String showProductPage(Model model, @PathVariable Long id) {
        Product product = productRepository.findById(id);
        model.addAttribute("product", product);
        return "product_info_page";
    }

}

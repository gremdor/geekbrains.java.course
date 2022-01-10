package ru.geekbrains.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String getTitleById(Long id) {
        return productRepository.findById(id).getName();
    }

    public Product getProductById (Long id) {
        return productRepository.findById(id);
    }

    public void printProducts () {
        System.out.println("List of products:");
        for (Product p : productRepository.getAllProducts()){
            System.out.println(p);
        }
    }
}

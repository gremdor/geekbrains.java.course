package ru.geekbrains.context;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@ComponentScan("ru.geekbrains.context")
public class AppConfig {
//    @Bean
//    public ProductService productService() {
//        return new ProductService();
//    }

    @Bean(name="cart")
    @Scope(BeanDefinition.SCOPE_PROTOTYPE)
    public CartService cart (ProductService productService){
        CartService cart = new CartService();
        cart.setProductService(productService);
        return cart;
    }
}

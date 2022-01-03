package ru.geekbrains.context;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        CartService cartService = context.getBean("cart", CartService.class);

        System.out.println("===> First cart");
        cartService.addProductByIdToCart(1L);
        cartService.addProductByIdToCart(1L);
        cartService.addProductByIdToCart(1L);
        cartService.addProductByIdToCart(2L);
        cartService.addProductByIdToCart(4L);
        cartService.printCart();
        cartService.removeProductByIdFromCart(1L);
        cartService.removeProductByIdFromCart(4L);
        cartService.printCart();

        System.out.println("===> Second cart");
        cartService = context.getBean("cart", CartService.class);
        cartService.printCart();
        cartService.addProductByIdToCart(3L);
        cartService.addProductByIdToCart(4L);
        cartService.printCart();
        cartService.removeProductByIdFromCart(1L);
        cartService.printCart();
    }
}

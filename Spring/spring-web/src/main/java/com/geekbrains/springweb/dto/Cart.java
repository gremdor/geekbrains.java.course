package com.geekbrains.springweb.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private float totalPrice;

    public Cart () {
        items = new ArrayList<>();
    }

    public boolean addProduct (Long productId) {
        for (OrderItemDto o : items){
            if (o.getProductId().equals(productId)) {
                o.changeQuantity(1);
                calcTotalPrice();
                return true;
            }
        }
        return false;
    }

    public void addProduct (ProductDto productDto) {
        if (addProduct(productDto.getId())) {
            return;
        }

        items.add(new OrderItemDto(productDto));
        calcTotalPrice();
    }

    public void removeProduct (Long productId) {
        items.removeIf(o -> o.getProductId().equals(productId));
        calcTotalPrice();
    }

    private void calcTotalPrice () {
        totalPrice = 0;
        for (OrderItemDto o : items){
            totalPrice += o.getPrice();
        }
    }
}

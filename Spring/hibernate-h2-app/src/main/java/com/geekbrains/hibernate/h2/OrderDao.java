package com.geekbrains.hibernate.h2;

import java.util.List;

public interface OrderDao {
    List<Order> findAll();
}

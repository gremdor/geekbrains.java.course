package com.geekbrains.hibernate.h2;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDaoImpl implements  OrderDao {
    private SessionFactoryUtils sessionFactoryUtils;

    public OrderDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public List<Order> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Order> orders = session.createQuery("select o from Order o").getResultList();
            session.getTransaction().commit();
            return orders;
        }

    }
}

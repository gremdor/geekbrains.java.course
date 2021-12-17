package com.geekbrains.hibernate.h2;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClientDaoImpl implements ClientDao{
    private SessionFactoryUtils sessionFactoryUtils;

    public ClientDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

    @Override
    public List<Client> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Client> clients = session.createQuery("select c from Client c").getResultList();
            session.getTransaction().commit();
            return clients;
        }
    }

    @Override
    public List<Product> findWhatBought(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select o.product from Client c join c.orders o where c.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return products;
        }

    }
}

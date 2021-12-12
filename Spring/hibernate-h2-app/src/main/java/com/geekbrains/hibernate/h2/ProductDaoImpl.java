package com.geekbrains.hibernate.h2;

import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

//2. Создайте класс ProductDao и реализуйте в нем логику выполнения CRUD-операций над сущностью Product
// Product findById(Long id),
// List<Product> findAll(),
// void deleteById(Long id),
// Product saveOrUpdate(Product product));

@Component
public class ProductDaoImpl implements ProductDao {
    private SessionFactoryUtils sessionFactoryUtils;

    public ProductDaoImpl(SessionFactoryUtils sessionFactoryUtils) {
        this.sessionFactoryUtils = sessionFactoryUtils;
    }

//    @Override
//    public Product findById(Long id) {
//        try (Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
//            Product product = session.get(Product.class, id);
//            session.getTransaction().commit();
//            return product;
//        }
//    }
//
//
    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Product> products = session.createQuery("select p from Product p").getResultList();
            session.getTransaction().commit();
            return products;
        }
    }
//
//    @Override
//    public void saveOrUpdate(Product product) {
//        try (Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
//            session.saveOrUpdate(product);
//            session.getTransaction().commit();
//        }
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        try (Session session = sessionFactoryUtils.getSession()) {
//            session.beginTransaction();
////            Product product = session.get(Product.class, id);
////            session.delete(product);
//            session.createQuery("delete Product p where p.id = :id")
//                    .setParameter("id", id)
//                    .executeUpdate();
//            session.getTransaction().commit();
//        }
//    }

    @Override
    public List<Client> findWhoBought(Long id) {
        try (Session session = sessionFactoryUtils.getSession()) {
            session.beginTransaction();
            List<Client> clients = session.createQuery("select c from Client c join c.orders o join o.product p where p.id = :id")
                    .setParameter("id", id)
                    .getResultList();
            session.getTransaction().commit();
            return clients;
        }
    }
}
package com.geekbrains.hibernate.h2;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDao productDao = new ProductDaoImpl(sessionFactoryUtils);

            System.out.println(productDao.findAll());

            Product pr = productDao.findById(2L);
            System.out.println(pr);
            pr.setPrice(pr.getPrice() + 13);
            pr.setTitle(pr.getTitle()+"_new");
            productDao.saveOrUpdate(pr);

            productDao.deleteById(3L);

            System.out.println(productDao.findAll());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}

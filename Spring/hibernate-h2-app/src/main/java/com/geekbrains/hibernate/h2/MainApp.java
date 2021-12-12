package com.geekbrains.hibernate.h2;

public class MainApp {
    public static void main(String[] args) {
        SessionFactoryUtils sessionFactoryUtils = new SessionFactoryUtils();
        sessionFactoryUtils.init();
        try {
            ProductDao productDao = new ProductDaoImpl(sessionFactoryUtils);
            ClientDao clientDao = new ClientDaoImpl(sessionFactoryUtils);
            OrderDao orderDao = new OrderDaoImpl(sessionFactoryUtils);

            System.out.println("=====  List of Products  =====");
            System.out.println(productDao.findAll());
            System.out.println("=====  List of Clients  =====  ");
            System.out.println(clientDao.findAll());
            System.out.println("=====  List of Orders  =====  ");
            System.out.println(orderDao.findAll());

            System.out.println("=====  List of Clients who bought the Product id=2 =====  ");
            System.out.println(productDao.findWhoBought(2L));
            System.out.println("=====  List of Products that were bought by Client id=4 =====  ");
            System.out.println(clientDao.findWhatBought(4L));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactoryUtils.shutdown();
        }
    }
}

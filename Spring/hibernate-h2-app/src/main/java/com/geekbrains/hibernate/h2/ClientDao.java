package com.geekbrains.hibernate.h2;

import java.util.List;

public interface ClientDao {
//    Client findById(Long id);
    List<Client> findAll();
//    void deleteById (Long id);
//    void saveOrUpdate(Client client);
    List<Product> findWhatBought(Long id);
}

package com.geekbrains.springweb.repositories;

import com.geekbrains.springweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long> {
    // @Query("select s from Student s where s.score between ?1 and ?2")
    @Query("select p from Product p where p.cost between ?1 and ?2")
    List<Product> findAllByCostBetween(Float min, Float max);
}

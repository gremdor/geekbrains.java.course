package com.geekbrains.springweb.repositories;

import com.geekbrains.springweb.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, Long>, JpaSpecificationExecutor<Product> {
//    @Query("select p from Product p where p.cost between ?1 and ?2")
//    List<Product> findAllByCostBetween(Float min, Float max);

//    @Query("select count() from Product p")
//    long count();
}

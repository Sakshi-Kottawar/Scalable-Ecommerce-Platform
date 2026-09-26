package org.example.productservice.repositories;

import org.example.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    //create and update is done using same method
    //if product u try to save in has id
    // jpa will see if product with that id exist
    // if yes-->it will update
    // else save or insert(including id as well)
    Product save(Product p);

    void delete(Product entity);

    List<Product> findAll();

}

package org.example.productservice.services;

import org.example.productservice.models.Product;

import java.util.*;

public interface ProductService {
    Product createProduct(Product product);

    Product getSingleProduct(Long id);

    Product updateProduct(Long id, Product product);

    Product replaceProduct(Long id, Product product);

    void deleteProduct(Long id);

    List<Product> getAllProducts();
}

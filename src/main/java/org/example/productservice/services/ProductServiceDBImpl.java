package org.example.productservice.services;

import org.example.productservice.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DbImplProductService")
public class ProductServiceDBImpl implements ProductService{

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product getSingleProduct(Long id) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }
}

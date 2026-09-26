package org.example.productservice.services;

import org.example.productservice.models.Category;
import org.example.productservice.models.Product;
import org.example.productservice.repositories.CategoryRepository;
import org.example.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("fakeStoreService")
public class ProductServiceImpl implements ProductService {

    private final FakeStoreProductClient productClient;


    public ProductServiceImpl(FakeStoreProductClient productClient) {
        this.productClient = productClient;
    }

    @Override
    @Cacheable("products")
    public ResponseEntity<BaseResponse> fetchAllProducts() {
        try{
            List<Product> products = productClient.getAllProducts();
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        }catch (Exception e){
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductById(Long id) {
        try {
            Product product = productClient.getProductById(id);
            return BaseResponse.getSuccessResponse(product).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchAllCategories() {
        try {
            List<String> categories = productClient.getAllCategories();
            return BaseResponse.getSuccessResponse(categories).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> fetchProductsByCategory(String category) {
        try {
            List<Product> products = productClient.getProductsByCategory(category);
            return BaseResponse.getSuccessResponse(products).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> createProduct(Product product) {
        try {
            Product createdProduct = productClient.addProduct(product);
            return BaseResponse.getSuccessResponse(createdProduct).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> modifyProduct(Long id, Product product) {
        try {
            Product updatedProduct = productClient.updateProduct(id, product);
            return BaseResponse.getSuccessResponse(updatedProduct).toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }

    @Override
    public ResponseEntity<BaseResponse> removeProduct(Long id) {
        try {
            productClient.deleteProduct(id);
            return BaseResponse.getSuccessResponse("Product successfully deleted").toResponseEntity();
        } catch (Exception e) {
            return BaseResponse.getErrorResponse(e.getMessage()).toResponseEntity();
        }
    }
}
package org.example.productservice.services;

import org.example.productservice.dtos.fakestore.FakeStoreCreateProductRequestDto;
import org.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import org.example.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Service("fakeStoreProductService")
//@Primary
public class ProductServiceFakeStoreImpl  implements  ProductService {
    private RestTemplate restTemplate;

    public ProductServiceFakeStoreImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public Product createProduct(Product product) {
        FakeStoreCreateProductRequestDto request = new FakeStoreCreateProductRequestDto();
//        request.setCategory(product.getCategory().getName());
        request.setTitle(product.getTitle());
        request.setImage(product.getImageUrl());
        request.setDescription(product.getDescription());
        request.setPrice(product.getPrice());

        FakeStoreGetProductResponseDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",
                request,
                FakeStoreGetProductResponseDto.class
        );
        return response.toProduct();
    }

    @Override
    public Product getSingleProduct(Long id) {
        FakeStoreGetProductResponseDto response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/{id}",
                FakeStoreGetProductResponseDto.class,
                id
        );
        return response.toProduct();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreCreateProductRequestDto request =FakeStoreCreateProductRequestDto.fromProduct(product);
        HttpEntity<FakeStoreCreateProductRequestDto> entity = new HttpEntity<>(request);

        ResponseEntity<FakeStoreGetProductResponseDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PATCH,
                entity,
                FakeStoreGetProductResponseDto.class,
                id
        );
        return response.getBody().toProduct();
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreCreateProductRequestDto request =
                FakeStoreCreateProductRequestDto.fromProduct(product);

        HttpEntity<FakeStoreCreateProductRequestDto> entity = new HttpEntity<>(request);

        ResponseEntity<FakeStoreGetProductResponseDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/{id}",
                HttpMethod.PUT,
                entity,
                FakeStoreGetProductResponseDto.class,
                id
        );

        return response.getBody().toProduct();
    }

    @Override
    public void deleteProduct(Long id) {
        restTemplate.delete("https://fakestoreapi.com/products/{id}", id);
    }

    @Override
    public List<Product> getAllProducts() {
         FakeStoreGetProductResponseDto[]  response=restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                 FakeStoreGetProductResponseDto[].class
        );
         /*why array is used-->at runTime java remove the genric class from list and why reading the outptut
         java does not know whihc class to convert it will just see list.class whihc is vague

         */


         List<FakeStoreGetProductResponseDto> responseDtosList= Stream.of(response).toList();
    List<Product> products=new ArrayList<>();
        for(FakeStoreGetProductResponseDto fakeStoreGetProductResponseDto:responseDtosList){
            products.add(fakeStoreGetProductResponseDto.toProduct());
        }

         return products;
    }
}

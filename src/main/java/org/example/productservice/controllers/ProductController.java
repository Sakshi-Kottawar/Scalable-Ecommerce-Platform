package org.example.productservice.controllers;

import org.example.productservice.dtos.fakestore.FakeStoreGetProductResponseDto;
import org.example.productservice.dtos.fakestore.GetProductResponseDto;
import org.example.productservice.dtos.products.CreateProductRequestDto;
import org.example.productservice.dtos.products.CreateProductResponseDto;
import org.example.productservice.models.Product;
import org.example.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductService") ProductService productService){
        this.productService = productService;
    }

    @PostMapping("")
    public CreateProductResponseDto createProduct(@RequestBody CreateProductRequestDto productRequestDto){
        Product product=productService.createProduct(productRequestDto.toProduct());
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Product could not be created");
        }
        return CreateProductResponseDto.fromProduct(product);
    }

    @GetMapping("")
    public List<GetProductResponseDto> getAllProducts(){
        List<Product> products=productService.getAllProducts();
        List<GetProductResponseDto> getProductResponseDtos=new ArrayList<>();

        for(Product product:products){
            getProductResponseDtos.add(GetProductResponseDto.from(product));

        }
        return getProductResponseDtos;
    }

    @GetMapping("/{id}")
    public  CreateProductResponseDto getSingleProducts(@PathVariable Long id){
        Product product = productService.getSingleProduct(id);
        if (product == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        return CreateProductResponseDto.fromProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProducts(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @PatchMapping("/{id}")//pathc
    public CreateProductResponseDto updateProduct(@PathVariable Long id, @RequestBody CreateProductRequestDto productRequestDto){
        Product updated = productService.updateProduct(id, productRequestDto.toProduct());
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        return CreateProductResponseDto.fromProduct(updated);
    }

    @PutMapping ("/{id}")//pathc
    public CreateProductResponseDto replaceProduct(@PathVariable Long id, @RequestBody CreateProductRequestDto productRequestDto){
         Product updated = productService.replaceProduct(id, productRequestDto.toProduct());
        if (updated == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found with id: " + id);
        }
        return CreateProductResponseDto.fromProduct(updated);
    }

    @ExceptionHandler(RuntimeException.class)
    public String handleException(){
        return "Something went wrong";
    }
}

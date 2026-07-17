package org.example.productservice.dtos.fakestore;


import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Product;

@Getter
@Setter
public class GetProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public static  GetProductResponseDto from(Product product) {
        GetProductResponseDto getProductResponseDto=new GetProductResponseDto();
        getProductResponseDto.setId(product.getId());
        getProductResponseDto.setDescription(product.getDescription());
        getProductResponseDto.setImage(product.getImageUrl());
        getProductResponseDto.setPrice(product.getPrice());
        getProductResponseDto.setCategory(product.getCategory());
        getProductResponseDto.setTitle(product.getTitle());

        return getProductResponseDto;
    }}
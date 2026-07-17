package org.example.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Product;

@Getter
@Setter
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;

    public static CreateProductResponseDto fromProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("product cannot be null");
        }

        CreateProductResponseDto responseDto = new CreateProductResponseDto();
        responseDto.setId(product.getId());
        responseDto.setDescription(product.getDescription());
        responseDto.setTitle(product.getTitle());
        responseDto.setPrice(product.getPrice());
        responseDto.setImageUrl(product.getImageUrl());

        return responseDto;
    }

}

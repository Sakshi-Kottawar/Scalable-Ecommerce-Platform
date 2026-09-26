package org.example.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Getter
@Setter
public class CreateProductResponseDto {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

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
        responseDto.setCategoryName(product.getCategory().getName());
        return responseDto;
    }

}

package org.example.productservice.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Getter
@Setter
public class FakeStoreCreateProductRequestDto {
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public static FakeStoreCreateProductRequestDto fromProduct(Product product) {
        FakeStoreCreateProductRequestDto fakeStoreCreateProductRequestDto = new FakeStoreCreateProductRequestDto();
        fakeStoreCreateProductRequestDto.title = product.getTitle();
        fakeStoreCreateProductRequestDto.description = product.getDescription();
        Category category = product.getCategory();
        fakeStoreCreateProductRequestDto.category = category == null ? null : category.getName();
        fakeStoreCreateProductRequestDto.price = product.getPrice();
        fakeStoreCreateProductRequestDto.image = product.getImageUrl();

        return fakeStoreCreateProductRequestDto;
    }
}

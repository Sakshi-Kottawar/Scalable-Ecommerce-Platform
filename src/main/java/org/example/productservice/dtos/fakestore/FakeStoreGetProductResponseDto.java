package org.example.productservice.dtos.fakestore;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Getter
@Setter
public class FakeStoreGetProductResponseDto {
    private Long id;
    private String title;
    private double price;
    private String image;
    private String description;
    private String category;

    public Product toProduct() {
        Product product1 = new Product();
        product1.setId(this.getId());
        product1.setTitle(this.getTitle());
        product1.setDescription(this.getDescription());
        product1.setImageUrl(this.getImage());
        Category category = new Category();
        category.setName(this.getCategory());
        product1.setCategory(category);
        product1.setPrice(this.getPrice());

        return product1;
    }
}

package org.example.productservice.dtos.products;

import lombok.Getter;
import lombok.Setter;
import org.example.productservice.models.Category;
import org.example.productservice.models.Product;

@Getter
@Setter
public class CreateProductRequestDto {
    private String title;
    private String description;
    private double price;
    private String imageUrl;
    private String categoryName;

    public Product toProduct() {
        Product product = new Product();
        product.setTitle(this.title);
        product.setDescription(this.description);
        product.setPrice(this.price);
        product.setImageUrl(this.imageUrl);
        Category category = new Category();
        category.setName(categoryName);
        product.setCategory(category);

        return product;
    }
}

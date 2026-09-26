package org.example.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
    @OneToMany
    List<Product> featuredProducts;
    @OneToMany(mappedBy = "category")
    List<Product> allProducts;


}

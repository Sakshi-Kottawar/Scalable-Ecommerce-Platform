package org.example.productservice.models;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
//
@Getter
@Setter
//@Entity
public class Product {
    public Object set;
    private String title;
    private String description;
    private Double price;
    private String imageUrl;
    private Long id;
    private String category;

//    @ManyToOne(cascade = {CascadeType.PERSIST})
//    private Category category;
}

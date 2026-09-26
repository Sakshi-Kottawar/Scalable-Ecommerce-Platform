package org.example.productservice.models;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Setter
@Getter
@MappedSuperclass
public class BaseModel {
    //MappedSuperclass -if annoted spring tells don't anNOTATE and put all the attributes in child classes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Date createdAt;
    private Date lastUpdated;
    private boolean isDeleted;
}

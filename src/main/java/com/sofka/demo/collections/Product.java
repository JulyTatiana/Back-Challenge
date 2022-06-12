package com.sofka.demo.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "product")
@Data
public class Product {

    @Id
    private String productId;
    @NotBlank(message = "Product's name is empty")
    private String productName;
    @NotBlank(message = "Product's description is empty")
    private String productDescription;
    @NotBlank(message = "Product's minimum quantity is empty")
    private Integer minQuantity;
    @NotBlank(message = "Product's maximum quantity is empty")
    private Integer maxQuantity;
    @NotBlank(message = "Units can't be empty")
    private Integer availableUnits = 0;
    @NotBlank(message = "Product price is empty")
    private Integer productPrice;
    @NotBlank(message = "Provider ID is empty")
    private String providerId;
}
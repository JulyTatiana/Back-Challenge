package com.sofka.demo.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DTOproduct {

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
    private Integer availableUnits;
    @NotBlank(message = "Product's price is empty")
    private Integer productPrice;
    @NotBlank(message = "Provider id is empty")
    private String providerId;

}

package com.sofka.demo.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DTOreceipt {

    private String receiptId;
    @NotBlank(message = "purveyor id is empty")
    private String providerId;
    private String date;
    @NotBlank(message = "units is empty")
    private Integer productUnits;
    @NotBlank(message = "product id is empty")
    private String productId;
}

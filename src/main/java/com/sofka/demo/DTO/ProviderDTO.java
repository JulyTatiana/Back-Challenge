package com.sofka.demo.DTO;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ProviderDTO {

    private String providerId;
    @NotBlank(message = "Provider Name is empty")
    private String providerName;
    @NotBlank(message = "Provider Id is empty")
    private String providerIdentification;
    @NotBlank (message = "Address is empty")
    private String providerAddress;
}

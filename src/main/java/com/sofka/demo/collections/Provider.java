package com.sofka.demo.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

@Document(collection = "provider")
@Data
public class Provider {

    @Id
    private String providerId;
    @NotBlank(message = "Provider name is empty")
    private String providerName;
    @NotBlank(message = "Provider id is empty")
    private String providerIdentification;
    @NotBlank(message = "Address is empty")
    private String providerAddress;

}

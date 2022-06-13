package com.sofka.demo.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Document(collection = "receipt")
@Data
public class Receipt {

    @Id
    private String receiptId;
    @NotBlank(message = "Id's provider is empty")
    private String providerId;
    private String date;
    @NotBlank(message = "units is empty")
    private Integer productUnits;
    @NotBlank(message = "products is empty")
    private String productId;
}
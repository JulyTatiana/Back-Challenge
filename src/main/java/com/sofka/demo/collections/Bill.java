package com.sofka.demo.collections;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "bill")
public class Bill {

    @Id
    private String id;
    @NotBlank(message = "Date is empty")
    private String date;
    @NotBlank(message = "Client is empty")
    private String client;
    @NotBlank(message = "Seller is empty")
    private String seller;
    @NotBlank(message = "Paid is empty")
    private Integer totalPaid;
    @NotBlank(message = "Product is empty")
    private List<String> productsId;
}
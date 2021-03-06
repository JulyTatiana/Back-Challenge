package com.sofka.demo.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class DTObill {

    private String id;
    @NotBlank(message = "Date is empty")
    private String date;
    @NotBlank(message = "Client is empty")
    private String client;
    @NotBlank(message = "Seller is empty")
    private String seller;
    @NotBlank(message = "Paid is empty")
    private Integer totalPaid;
    @NotBlank(message = "Products is empty")
    private String productId;
    
}

package com.example.models.dtos.api;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
@Builder

public class CreateOrderRequest {

    private String resturantId;
    private String customerId;
    private List<CreateOrderItem> orderItems;


}
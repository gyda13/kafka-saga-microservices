package com.example.models.dtos.kafka;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;



@Serdeable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreated {
    private String resturantId;
    private String customerId;
    private Long totalPrice;
    private Long orderId;

}

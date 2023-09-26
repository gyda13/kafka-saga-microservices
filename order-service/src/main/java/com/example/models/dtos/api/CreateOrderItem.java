package com.example.models.dtos.api;


import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class CreateOrderItem {
    private String brand;
    private String name;
    private Long quantity;
    private Long price;


}

package com.example.models.api;

import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class CreateCustomerResponse {
    private String customerId;
    private Long balance;
}

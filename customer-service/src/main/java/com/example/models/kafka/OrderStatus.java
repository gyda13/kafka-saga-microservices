package com.example.models.kafka;
import io.micronaut.serde.annotation.Serdeable;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Serdeable
public class OrderStatus {
    private Long orderId;
    private String status;
}

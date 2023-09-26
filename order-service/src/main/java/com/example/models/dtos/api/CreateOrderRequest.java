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

    public String getResturantId() {
        return resturantId;
    }

    public void setResturantId(String resturantId) {
        this.resturantId = resturantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<CreateOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<CreateOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
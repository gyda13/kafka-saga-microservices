package com.example.listeners;

import com.example.models.OrderCreated;
import com.example.services.CustomerService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.Topic;

import java.util.concurrent.TimeUnit;

@KafkaListener(offsetReset = OffsetReset.EARLIEST)
public class OrderCreatedListener {

    private final CustomerService customerService;

    public OrderCreatedListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Topic("createdOrder")
    public void processOrderCreated(OrderCreated orderCreated){

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.customerService.processOrder(orderCreated);

    }
}

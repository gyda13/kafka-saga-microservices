package com.example.clients;

import com.example.models.dtos.kafka.OrderCreated;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface CustomerClient {

    @Topic("createdOrder")
    void createOrder(OrderCreated orderCreated);
}

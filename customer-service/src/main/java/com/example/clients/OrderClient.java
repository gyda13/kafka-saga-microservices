package com.example.clients;

import com.example.models.kafka.OrderStatus;
import io.micronaut.configuration.kafka.annotation.KafkaClient;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaClient
public interface OrderClient {

//    kafka-console-consumer --bootstrap-server localhost:9092 --topic approvedOrder --from-beginning
//
//    kafka-console-consumer --bootstrap-server localhost:9092 --topic rejectedOrder --from-beginning

    @Topic("approvedOrder")
    void approvedOrder(OrderStatus approvedOrder);

    @Topic("rejectedOrder")
    void rejectedOrder(OrderStatus rejectedOrder);

}

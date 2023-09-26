package com.example.listeners;

import com.example.models.dtos.kafka.OrderStatus;
import com.example.services.OrderService;
import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.Topic;

@KafkaListener
public class OrderListener {


    private final OrderService orderService;

    public OrderListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @Topic("approvedOrder")
    public void approvedOrder(OrderStatus orderStaus){

        System.out.println("Recived Approved Order Event");
        this.orderService.approveOrder(orderStaus);

    }
    @Topic("rejectedOrder")
    public void rejectedOrder(OrderStatus orderStaus){
        System.out.println("Rejected Approved Order Event");
        this.orderService.approveOrder(orderStaus);

    }

}

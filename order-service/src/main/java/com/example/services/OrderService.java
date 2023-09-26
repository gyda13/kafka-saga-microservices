package com.example.services;


import com.example.clients.CustomerClient;
import com.example.daos.OrderDAO;
import com.example.daos.OrderItemDAO;
import com.example.mappers.OrderMapper;
import com.example.models.Order;
import com.example.models.OrderItem;
import com.example.models.dtos.api.CreateOrderRequest;
import com.example.models.dtos.api.CreateOrderResponse;
import com.example.models.dtos.kafka.OrderStatus;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.List;


@Singleton
public class OrderService {

    private final OrderDAO orderDAO;
    private final OrderItemDAO orderItemDAO;

    private final CustomerClient customerClient;

    public OrderService(OrderDAO orderDAO, OrderItemDAO orderItemDAO, CustomerClient customerClient) {
        this.orderDAO = orderDAO;
        this.orderItemDAO = orderItemDAO;
        this.customerClient = customerClient;
    }

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest createOrderRequest)
    {
        Order order = OrderMapper.INSTANCE.toOrder(createOrderRequest);
        this.orderDAO.save(order);
        System.out.println("Order Details: "+ order);

        List<OrderItem> orderItems = OrderMapper.INSTANCE.toOrderItem(createOrderRequest.getOrderItems());

        // set order id for all order items
        setOderIdtoOrderItems(order,orderItems);

        //save the order items
        Iterable< OrderItem> orderItemsCreated = this.orderItemDAO.saveAll(orderItems);


//        System.out.println("Returned order:"  + this.orderItemDAO.findByOrderId(Long.valueOf(12)));
        this.customerClient.createOrder(
                OrderMapper.INSTANCE.toOrderCreated(order)
        );


        return CreateOrderResponse.builder()
                .trackId(order.getTrackId())
                .build();

    }


    public void approveOrder(OrderStatus orderStatus){
        this.orderDAO.updatestatusById(orderStatus.getOrderId(), orderStatus.getStatus());
    }
    public void rejectOrder(OrderStatus orderStatus){
        this.orderDAO.updatestatusById(orderStatus.getOrderId(), orderStatus.getStatus());
    }
    public void setOderIdtoOrderItems(Order order,List<OrderItem> orderItems)
    {
        orderItems.forEach(orderItem -> {
            orderItem.setOrder(order);
        });
    }
}


package com.example.mappers;

import com.example.models.Order;
import com.example.models.OrderItem;
import com.example.models.dtos.api.CreateOrderItem;
import com.example.models.dtos.api.CreateOrderRequest;
import com.example.models.dtos.kafka.OrderCreated;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.UUID;


@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "name", target = "name", qualifiedByName = "noChange")
    @Mapping(source = "quantity", target = "quantity", qualifiedByName = "noChange")
    @Mapping(source = "brand", target = "brand", qualifiedByName = "noChange")
    OrderItem toOrderItem(CreateOrderItem createOrderItems);

    List<OrderItem> toOrderItem(List<CreateOrderItem> createOrderItems);

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "noChange")
    @Mapping(source = "resturantId", target = "resturantId", qualifiedByName = "noChange")
    @Mapping(target = "totalPrice", expression = "java(calcTotalPrice(createOrderRequest.getOrderItems()))")
    @Mapping(target = "trackId", expression = "java(genUUID())")
    @Mapping(target = "status", expression = "java(getPending())")
    Order toOrder(CreateOrderRequest createOrderRequest);


    @Mapping(source = "resturantId", target = "resturantId", qualifiedByName = "noChange")
    @Mapping(source = "id", target = "orderId", qualifiedByName = "noChange")
    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "noChange")
    OrderCreated toOrderCreated(Order order);


    @Named("noChange")
    static String noChange(String data) {

        return data;
    }
    @Named("noChange")
    static Long noChange(Long data) {

        return data;
    }
    default String genUUID() {

        return UUID.randomUUID().toString();
    }
    default String getPending() {

        return "Pending";
    }

    default Long calcTotalPrice(List<CreateOrderItem> createOrderItems){

        return createOrderItems.stream().map(orderItem -> orderItem.getPrice())
                .reduce(Long.valueOf(0), (a, b) -> a + b);
    }
}

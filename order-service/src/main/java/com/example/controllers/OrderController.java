package com.example.controllers;

import com.example.models.dtos.api.CreateOrderRequest;
import com.example.models.dtos.api.CreateOrderResponse;
import com.example.services.OrderService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;

@Controller
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @Operation(description = "Create a new order")
    @ApiResponse(responseCode = "200", description = "Status returned if order is created successfully",
            content = @Content(schema = @Schema(implementation = CreateOrderResponse.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "400", description = "Bad request")
    @ApiResponse(responseCode = "500", description = "Internal Server Error")
    @Tags(@Tag(name = "PUBLIC_RELEASED"))
    @ExecuteOn(TaskExecutors.IO)
    @Post("createOrder")
    public HttpResponse<CreateOrderResponse> createOrder(@Body CreateOrderRequest createOrderRequest)
    {
        return HttpResponse.ok(
                this.orderService.createOrder(createOrderRequest)
        );
    }
}

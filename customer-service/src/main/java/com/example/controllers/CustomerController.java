package com.example.controllers;


import com.example.models.api.CreateCustomerRequest;
import com.example.models.api.CreateCustomerResponse;
import com.example.services.CustomerService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

@Controller

public class CustomerController {


    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Post("createCustomer")
    public HttpResponse<CreateCustomerResponse> createCustomer(@Body CreateCustomerRequest createCustomerRequest)
    {
        return HttpResponse.ok (this.customerService.createCustomer(createCustomerRequest));
    }

}
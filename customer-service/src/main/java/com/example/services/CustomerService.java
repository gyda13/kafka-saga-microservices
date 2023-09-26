package com.example.services;

import com.example.clients.OrderClient;
import com.example.daos.CustomerDAO;
import com.example.mappers.CustomerMapper;
import com.example.models.Customer;
import com.example.models.OrderCreated;
import com.example.models.api.CreateCustomerRequest;
import com.example.models.api.CreateCustomerResponse;
import com.example.models.kafka.OrderStatus;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

import java.util.Optional;

@Singleton
public class CustomerService {

    private final CustomerDAO customerDAO;

    private final OrderClient orderClient;

    public CustomerService(CustomerDAO customerDAO, OrderClient orderClient) {
        this.customerDAO = customerDAO;
        this.orderClient = orderClient;
    }


    @Transactional
    public void processOrder(OrderCreated orderCreated){

        Optional<Customer> retrivedCustomer = this.customerDAO.findByCustomerId(orderCreated.getCustomerId());

        if(retrivedCustomer.isPresent()){
            //check balance
            if(retrivedCustomer.get().getBalance() >= orderCreated.getTotalPrice()){
                Long newBalance = retrivedCustomer.get().getBalance() - orderCreated.getTotalPrice();
                this.customerDAO.updateBalanceByCustomerId(retrivedCustomer.get().getCustomerId(), newBalance);
                sendOrderStatus("Approved",orderCreated.getOrderId());
            }else{
                sendOrderStatus("Rejected",orderCreated.getOrderId());
            }
        }else{
            sendOrderStatus("Rejected",orderCreated.getOrderId());
        }

    }

    public CreateCustomerResponse createCustomer(CreateCustomerRequest createCustomerRequest){
        Customer customer = CustomerMapper.INSTANCE.toCustomer(createCustomerRequest);
        Customer createCustomer = this.customerDAO.save(customer);
        return CustomerMapper.INSTANCE.toCreateCustomerResponse(createCustomer);


    }
    void sendOrderStatus(String status, Long orderId){
        this.orderClient.rejectedOrder(
                OrderStatus.builder()
                        .orderId(orderId)
                        .status(status)
                        .build()
        );
    }

}

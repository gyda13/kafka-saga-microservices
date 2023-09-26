package com.example.mappers;



import com.example.models.Customer;
import com.example.models.api.CreateCustomerRequest;
import com.example.models.api.CreateCustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper
public interface CustomerMapper {


    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    @Mapping(source = "balance", target = "balance", qualifiedByName = "noChange")
    @Mapping( target = "customerId", expression = "java(genUUID())")
    Customer toCustomer(CreateCustomerRequest createCustomerRequest);

    @Mapping(source = "customerId", target = "customerId", qualifiedByName = "noChange")
    @Mapping(source = "balance", target = "balance", qualifiedByName = "noChange")
    CreateCustomerResponse toCreateCustomerResponse(Customer customer);


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

}

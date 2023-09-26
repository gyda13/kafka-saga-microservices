package com.example.models;


import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import io.micronaut.data.annotation.MappedProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedEntity("customer")

public class Customer {
    @Id
    @MappedProperty( "customerid")
    private String customerId;
    private Long balance;

}


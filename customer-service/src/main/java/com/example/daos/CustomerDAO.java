package com.example.daos;


import com.example.models.Customer;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public interface CustomerDAO extends PageableRepository<Customer,String> {

    Optional<Customer> findByCustomerId(String customerId);

    void updateBalanceByCustomerId(@Id String customerId, Long balance);
}

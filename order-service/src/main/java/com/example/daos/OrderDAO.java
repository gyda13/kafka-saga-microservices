package com.example.daos;

import com.example.models.Order;
import com.example.models.OrderItem;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface OrderDAO extends PageableRepository<Order, Long> {

    Optional<Order> findById(Long id);
    List<OrderItem> findOrderItemsById(Long id);
    void updatestatusById(@Id Long id, String status);
}

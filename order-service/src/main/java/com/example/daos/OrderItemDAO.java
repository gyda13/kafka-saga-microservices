package com.example.daos;

import com.example.models.OrderItem;
import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.PageableRepository;

import java.util.List;

@JdbcRepository(dialect = Dialect.MYSQL)
public interface OrderItemDAO extends PageableRepository<OrderItem, Long> {

    List<OrderItem> findByOrderId(Long id);

}

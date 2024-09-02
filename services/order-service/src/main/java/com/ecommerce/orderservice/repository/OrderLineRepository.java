package com.ecommerce.orderservice.repository;

import com.ecommerce.orderservice.model.OrderLine;
import com.ecommerce.orderservice.model.response.OrderLineResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    @Query(value = "SELECT * FROM order_line WHERE order_id = :orderId", nativeQuery = true)
    List<OrderLine> findOrderLinesByOrderId(@Param("orderId") Integer orderId);

}

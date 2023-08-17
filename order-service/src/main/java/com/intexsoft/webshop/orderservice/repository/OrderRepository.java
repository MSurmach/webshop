package com.intexsoft.webshop.orderservice.repository;

import com.intexsoft.webshop.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

package com.intexsoft.webshop.orderservice.repository;

import com.intexsoft.webshop.orderservice.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}

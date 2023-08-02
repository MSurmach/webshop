package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
    Optional<Vendor> findByNameIgnoreCase(String name);
}

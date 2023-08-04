package com.intexsoft.webshop.productservice.repository;

import com.intexsoft.webshop.productservice.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}

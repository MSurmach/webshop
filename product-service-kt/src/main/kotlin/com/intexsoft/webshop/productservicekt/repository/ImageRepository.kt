package com.intexsoft.webshop.productservicekt.repository

import com.intexsoft.webshop.productservicekt.model.Image
import org.springframework.data.jpa.repository.JpaRepository

interface ImageRepository : JpaRepository<Image, Long>

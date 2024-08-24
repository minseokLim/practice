package com.minseoklim.toyproject2024.product.domain.repository

import com.minseoklim.toyproject2024.product.domain.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int>

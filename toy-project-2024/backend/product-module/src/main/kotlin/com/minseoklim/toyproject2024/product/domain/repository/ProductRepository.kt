package com.minseoklim.toyproject2024.product.domain.repository

import com.minseoklim.toyproject2024.product.domain.model.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Int> {
    fun findAllByMemberId(memberId: Int, pageable: Pageable): Page<Product>
}

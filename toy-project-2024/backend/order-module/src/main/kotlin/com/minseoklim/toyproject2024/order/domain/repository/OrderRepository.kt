package com.minseoklim.toyproject2024.order.domain.repository

import com.minseoklim.toyproject2024.order.domain.model.Order
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByMemberId(
        memberId: Long,
        pageable: Pageable
    ): Page<Order>
}

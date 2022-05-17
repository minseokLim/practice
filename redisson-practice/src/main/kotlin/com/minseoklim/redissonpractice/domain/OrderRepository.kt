package com.minseoklim.redissonpractice.domain

import org.springframework.data.jpa.repository.JpaRepository

interface OrderRepository : JpaRepository<Order, Long> {
    fun findAllByProductId(productId: Long): List<Order>
}

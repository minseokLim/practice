package com.minseoklim.redissonpractice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Product(
    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val totalCount: Int,

    stockCount: Int,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
) {
    @Column(nullable = false)
    var stockCount: Int = stockCount
        private set

    fun applyStockCount(count: Int) {
        stockCount = count
    }
}

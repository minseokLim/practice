package com.minseoklim.redissonpractice.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToOne
import javax.persistence.Table

@Entity
@Table(name = "orders")
class Order(
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val orderCount: Int,

    @ManyToOne
    val product: Product,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null
)

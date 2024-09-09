package com.minseoklim.toyproject2024.order.domain.model

data class Product(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int,
    val isDeleted: Boolean
)

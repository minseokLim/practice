package com.minseoklim.toyproject2024.product.dto.application

import com.minseoklim.toyproject2024.product.domain.model.Product

data class RegisterProductInput(
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    fun toEntity(memberId: Int): Product {
        return Product(
            name = name,
            price = price,
            stockQuantity = stockQuantity,
            memberId = memberId
        )
    }
}

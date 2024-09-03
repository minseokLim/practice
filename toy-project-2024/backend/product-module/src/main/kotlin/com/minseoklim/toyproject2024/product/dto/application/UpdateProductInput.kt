package com.minseoklim.toyproject2024.product.dto.application

import com.minseoklim.toyproject2024.product.domain.model.Product

data class UpdateProductInput(
    val name: String,
    val price: Long
) {
    fun toEntity(original: Product): Product {
        return Product(
            name = name,
            price = price,
            stockQuantity = original.stockQuantity.value,
            memberId = original.memberId
        )
    }
}

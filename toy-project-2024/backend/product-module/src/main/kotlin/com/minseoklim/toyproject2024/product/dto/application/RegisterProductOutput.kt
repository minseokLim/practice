package com.minseoklim.toyproject2024.product.dto.application

import com.minseoklim.toyproject2024.product.domain.model.Product

data class RegisterProductOutput private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(product: Product): RegisterProductOutput {
            return RegisterProductOutput(
                id = checkNotNull(product.id),
                name = product.name.value,
                price = product.price.value.toLong(),
                stockQuantity = product.stockQuantity.value
            )
        }
    }
}

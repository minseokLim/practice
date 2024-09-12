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
            return with(product) {
                RegisterProductOutput(
                    id = checkNotNull(id),
                    name = name.value,
                    price = price.value.toLong(),
                    stockQuantity = stockQuantity.value
                )
            }
        }
    }
}

package com.minseoklim.toyproject2024.product.dto.application

import com.minseoklim.toyproject2024.product.domain.model.Product

data class UpdateProductOutput private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(product: Product): UpdateProductOutput {
            return with(product) {
                UpdateProductOutput(
                    id = id!!,
                    name = name.value,
                    price = price.value.toLong(),
                    stockQuantity = stockQuantity.value
                )
            }
        }
    }
}

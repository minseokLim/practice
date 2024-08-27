package com.minseoklim.toyproject2024.product.dto

import com.minseoklim.toyproject2024.product.domain.model.Product

data class UpdateProductResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(product: Product): UpdateProductResponse {
            return with(product) {
                UpdateProductResponse(
                    id = id!!,
                    name = name.value,
                    price = price.value.toLong(),
                    stockQuantity = stockQuantity.value
                )
            }
        }
    }
}

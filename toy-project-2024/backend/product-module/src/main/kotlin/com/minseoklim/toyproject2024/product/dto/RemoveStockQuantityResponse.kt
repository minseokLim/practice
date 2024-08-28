package com.minseoklim.toyproject2024.product.dto

import com.minseoklim.toyproject2024.product.domain.model.Product

data class RemoveStockQuantityResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(product: Product): RemoveStockQuantityResponse {
            return with(product) {
                RemoveStockQuantityResponse(
                    id = id!!,
                    name = name.value,
                    price = price.value.toLong(),
                    stockQuantity = stockQuantity.value
                )
            }
        }
    }
}


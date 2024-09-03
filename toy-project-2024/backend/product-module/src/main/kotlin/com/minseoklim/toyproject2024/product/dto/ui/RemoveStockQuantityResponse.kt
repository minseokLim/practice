package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityOutput

data class RemoveStockQuantityResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(output: RemoveStockQuantityOutput): RemoveStockQuantityResponse {
            return with(output) {
                RemoveStockQuantityResponse(
                    id = id,
                    name = name,
                    price = price,
                    stockQuantity = stockQuantity
                )
            }
        }
    }
}

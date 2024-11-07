package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityOutput

data class RemoveStockQuantityResponse private constructor(
    val id: Long,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun from(output: RemoveStockQuantityOutput): RemoveStockQuantityResponse {
            return RemoveStockQuantityResponse(
                id = output.id,
                name = output.name,
                price = output.price,
                stockQuantity = output.stockQuantity
            )
        }
    }
}

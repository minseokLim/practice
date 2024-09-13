package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.AddStockQuantityOutput

data class AddStockQuantityResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(output: AddStockQuantityOutput): AddStockQuantityResponse {
            return AddStockQuantityResponse(
                id = output.id,
                name = output.name,
                price = output.price,
                stockQuantity = output.stockQuantity
            )
        }
    }
}

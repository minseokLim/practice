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
            return with(output) {
                AddStockQuantityResponse(
                    id = id,
                    name = name,
                    price = price,
                    stockQuantity = stockQuantity
                )
            }
        }
    }
}

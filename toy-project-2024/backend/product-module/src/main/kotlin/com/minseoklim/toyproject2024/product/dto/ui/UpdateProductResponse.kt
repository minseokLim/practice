package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.UpdateProductOutput

data class UpdateProductResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(output: UpdateProductOutput): UpdateProductResponse {
            return with(output) {
                UpdateProductResponse(
                    id = id,
                    name = name,
                    price = price,
                    stockQuantity = stockQuantity
                )
            }
        }
    }
}

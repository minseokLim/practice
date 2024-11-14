package com.minseoklim.toyproject2024.product.ui.dto

import com.minseoklim.toyproject2024.product.application.dto.RegisterProductOutput

data class RegisterProductResponse private constructor(
    val id: Long,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun from(output: RegisterProductOutput): RegisterProductResponse {
            return RegisterProductResponse(
                id = output.id,
                name = output.name,
                price = output.price,
                stockQuantity = output.stockQuantity
            )
        }
    }
}

package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.RegisterProductOutput

data class RegisterProductResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int
) {
    companion object {
        fun of(output: RegisterProductOutput): RegisterProductResponse {
            return with(output) {
                RegisterProductResponse(
                    id = id,
                    name = name,
                    price = price,
                    stockQuantity = stockQuantity
                )
            }
        }
    }
}
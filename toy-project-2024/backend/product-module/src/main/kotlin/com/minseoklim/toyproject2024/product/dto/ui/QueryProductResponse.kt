package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.QueryProductOutput

data class QueryProductResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun of(output: QueryProductOutput): QueryProductResponse {
            return QueryProductResponse(
                id = output.id,
                name = output.name,
                price = output.price,
                stockQuantity = output.stockQuantity,
                isDeleted = output.isDeleted
            )
        }
    }
}

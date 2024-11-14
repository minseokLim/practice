package com.minseoklim.toyproject2024.product.ui.dto

import com.minseoklim.toyproject2024.product.application.dto.QueryProductOutput

data class QueryProductResponse private constructor(
    val id: Long,
    val name: String,
    val price: Long,
    val stockQuantity: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun from(output: QueryProductOutput): QueryProductResponse {
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

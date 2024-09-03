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
            return with(output) {
                QueryProductResponse(
                    id = id,
                    name = name,
                    price = price,
                    stockQuantity = stockQuantity,
                    isDeleted = isDeleted
                )
            }
        }
    }
}

package com.minseoklim.toyproject2024.product.dto

import com.minseoklim.toyproject2024.product.domain.model.Product

data class QueryProductResponse private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun of(product: Product): QueryProductResponse {
            return with(product) {
                QueryProductResponse(
                    id = id!!,
                    name = name.value,
                    price = price.value.toLong(),
                    stockQuantity = stockQuantity.value,
                    isDeleted = isDeleted
                )
            }
        }
    }
}

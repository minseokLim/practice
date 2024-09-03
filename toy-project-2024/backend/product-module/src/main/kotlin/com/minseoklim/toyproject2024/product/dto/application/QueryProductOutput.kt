package com.minseoklim.toyproject2024.product.dto.application

import com.minseoklim.toyproject2024.product.domain.model.Product

data class QueryProductOutput private constructor(
    val id: Int,
    val name: String,
    val price: Long,
    val stockQuantity: Int,
    val isDeleted: Boolean
) {
    companion object {
        fun of(product: Product): QueryProductOutput {
            return with(product) {
                QueryProductOutput(
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

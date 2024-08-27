package com.minseoklim.toyproject2024.product.dto

import com.minseoklim.toyproject2024.product.domain.model.Name
import com.minseoklim.toyproject2024.product.domain.model.Price
import com.minseoklim.toyproject2024.product.domain.model.Product
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero

data class UpdateProductRequest(
    @get:NotBlank(message = Name.ERR_MSG)
    val name: String,

    @get:PositiveOrZero(message = Price.ERR_MSG)
    val price: Long
) {
    fun toEntity(original: Product): Product {
        return Product(
            name = name,
            price = price,
            stockQuantity = original.stockQuantity.value,
            memberId = original.memberId
        )
    }
}

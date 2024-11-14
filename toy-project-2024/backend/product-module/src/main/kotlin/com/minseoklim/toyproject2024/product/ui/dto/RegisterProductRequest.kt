package com.minseoklim.toyproject2024.product.ui.dto

import com.minseoklim.toyproject2024.product.application.dto.RegisterProductInput
import com.minseoklim.toyproject2024.product.domain.model.Name
import com.minseoklim.toyproject2024.product.domain.model.Price
import com.minseoklim.toyproject2024.product.domain.model.StockQuantity
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero

data class RegisterProductRequest(
    @get:NotBlank(message = Name.ERR_MSG)
    val name: String,

    @get:PositiveOrZero(message = Price.ERR_MSG)
    val price: Long,

    @get:PositiveOrZero(message = StockQuantity.ERR_MSG)
    val stockQuantity: Int
) {
    fun toInput(): RegisterProductInput {
        return RegisterProductInput(
            name = name,
            price = price,
            stockQuantity = stockQuantity
        )
    }
}

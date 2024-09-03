package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.domain.model.Name
import com.minseoklim.toyproject2024.product.domain.model.Price
import com.minseoklim.toyproject2024.product.dto.application.UpdateProductInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero

data class UpdateProductRequest(
    @get:NotBlank(message = Name.ERR_MSG)
    val name: String,

    @get:PositiveOrZero(message = Price.ERR_MSG)
    val price: Long
) {
    fun toInput(): UpdateProductInput {
        return UpdateProductInput(
            name = name,
            price = price
        )
    }
}

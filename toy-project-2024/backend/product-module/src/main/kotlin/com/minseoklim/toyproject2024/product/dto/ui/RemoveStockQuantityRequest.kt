package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityInput
import jakarta.validation.constraints.Positive

data class RemoveStockQuantityRequest(
    @get:Positive
    val decrement: Int
) {
    fun toInput(): RemoveStockQuantityInput {
        return RemoveStockQuantityInput(decrement)
    }
}

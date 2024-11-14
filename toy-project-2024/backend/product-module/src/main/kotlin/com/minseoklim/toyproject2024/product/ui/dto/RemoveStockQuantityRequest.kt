package com.minseoklim.toyproject2024.product.ui.dto

import com.minseoklim.toyproject2024.product.application.dto.RemoveStockQuantityInput
import jakarta.validation.constraints.Positive

data class RemoveStockQuantityRequest(
    @get:Positive
    val decrement: Int
) {
    fun toInput(): RemoveStockQuantityInput {
        return RemoveStockQuantityInput(decrement)
    }
}

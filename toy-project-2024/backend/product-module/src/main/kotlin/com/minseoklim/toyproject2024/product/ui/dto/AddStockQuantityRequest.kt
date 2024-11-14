package com.minseoklim.toyproject2024.product.ui.dto

import com.minseoklim.toyproject2024.product.application.dto.AddStockQuantityInput
import jakarta.validation.constraints.Positive

data class AddStockQuantityRequest(
    @get:Positive
    val increment: Int
) {
    fun toInput(): AddStockQuantityInput {
        return AddStockQuantityInput(increment)
    }
}

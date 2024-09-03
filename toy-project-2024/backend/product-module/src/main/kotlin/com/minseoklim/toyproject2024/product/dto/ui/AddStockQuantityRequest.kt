package com.minseoklim.toyproject2024.product.dto.ui

import com.minseoklim.toyproject2024.product.dto.application.AddStockQuantityInput
import jakarta.validation.constraints.Positive

data class AddStockQuantityRequest(
    @get:Positive
    val increment: Int
) {
    fun toInput(): AddStockQuantityInput {
        return AddStockQuantityInput(increment)
    }
}

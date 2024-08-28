package com.minseoklim.toyproject2024.product.dto

import jakarta.validation.constraints.Positive

data class RemoveStockQuantityRequest(
    @get:Positive
    val decrement: Int
)

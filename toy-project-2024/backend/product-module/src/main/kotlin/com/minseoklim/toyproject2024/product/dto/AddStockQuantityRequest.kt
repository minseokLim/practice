package com.minseoklim.toyproject2024.product.dto

import jakarta.validation.constraints.Positive

data class AddStockQuantityRequest(
    @get:Positive
    val increment: Int
)

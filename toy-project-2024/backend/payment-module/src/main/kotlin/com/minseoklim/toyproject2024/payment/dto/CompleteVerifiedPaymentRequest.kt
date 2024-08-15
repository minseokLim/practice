package com.minseoklim.toyproject2024.payment.dto

import jakarta.validation.constraints.NotBlank

data class CompleteVerifiedPaymentRequest(
    @get:NotBlank
    val paymentUid: String
)

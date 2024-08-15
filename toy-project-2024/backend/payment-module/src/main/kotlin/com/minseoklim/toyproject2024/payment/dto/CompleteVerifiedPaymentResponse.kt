package com.minseoklim.toyproject2024.payment.dto

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedPaymentResponse(
    val status: VerifiedPaymentStatus
)

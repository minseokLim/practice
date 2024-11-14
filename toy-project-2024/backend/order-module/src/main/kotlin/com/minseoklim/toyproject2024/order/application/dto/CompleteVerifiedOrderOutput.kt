package com.minseoklim.toyproject2024.order.application.dto

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedOrderOutput(
    val paymentStatus: VerifiedPaymentStatus
)

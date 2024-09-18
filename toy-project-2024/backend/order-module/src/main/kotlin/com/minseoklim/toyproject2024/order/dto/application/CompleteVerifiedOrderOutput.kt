package com.minseoklim.toyproject2024.order.dto.application

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedOrderOutput(
    val paymentStatus: VerifiedPaymentStatus
)

package com.minseoklim.toyproject2024.payment.application.dto

import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPaymentStatus

data class CompleteVerifiedPaymentOutput(
    val status: VerifiedPaymentStatus
)

package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.FailVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.ui.FailVerifiedPaymentRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FailVerifiedPaymentController(
    private val failVerifiedPaymentService: FailVerifiedPaymentService
) {
    @PostMapping("/fail-verified-payment")
    fun failVerifiedPayment(
        @MemberId memberId: Int,
        @Valid @RequestBody request: FailVerifiedPaymentRequest
    ) {
        failVerifiedPaymentService.failVerifiedPayment(memberId, request.toInput())
    }
}

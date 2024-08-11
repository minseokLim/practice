package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.payment.application.FailVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.FailVerifiedPaymentRequest
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FailVerifiedPaymentController(
    private val failVerifiedPaymentService: FailVerifiedPaymentService
) {
    @PostMapping("/fail-verified-payment")
    fun failVerifiedPayment(@Valid @RequestBody request: FailVerifiedPaymentRequest) {
        failVerifiedPaymentService.failVerifiedPayment(request)
    }
}

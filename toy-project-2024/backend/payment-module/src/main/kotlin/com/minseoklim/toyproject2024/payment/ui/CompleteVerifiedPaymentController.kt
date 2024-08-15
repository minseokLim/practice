package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.payment.application.CompleteVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.CompleteVerifiedPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.CompleteVerifiedPaymentResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompleteVerifiedPaymentController(
    private val completeVerifiedPaymentService: CompleteVerifiedPaymentService
) {
    @PostMapping("/complete-verified-payment")
    fun completeVerifiedPayment(@Valid @RequestBody request: CompleteVerifiedPaymentRequest): ResponseEntity<CompleteVerifiedPaymentResponse> {
        val response = completeVerifiedPaymentService.completeVerifiedPayment(request)
        return ResponseEntity.ok(response)
    }
}

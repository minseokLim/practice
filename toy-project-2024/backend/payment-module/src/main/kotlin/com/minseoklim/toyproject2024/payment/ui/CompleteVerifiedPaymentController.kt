package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.CompleteVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.ui.CompleteVerifiedPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.ui.CompleteVerifiedPaymentResponse
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
    fun completeVerifiedPayment(
        @MemberId memberId: Int,
        @Valid @RequestBody request: CompleteVerifiedPaymentRequest
    ): ResponseEntity<CompleteVerifiedPaymentResponse> {
        val output = completeVerifiedPaymentService.completeVerifiedPayment(memberId, request.toInput())
        return ResponseEntity.ok(CompleteVerifiedPaymentResponse.of(output))
    }
}

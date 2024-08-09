package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.CheckOutVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.CheckOutVerifiedPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.CheckOutVerifiedPaymentResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckOutVerifiedPaymentController(
    private val checkOutVerifiedPaymentService: CheckOutVerifiedPaymentService
) {
    @PostMapping("/verified-payments")
    fun checkOut(
        @MemberId memberId: Int,
        @Valid @RequestBody request: CheckOutVerifiedPaymentRequest
    ): ResponseEntity<CheckOutVerifiedPaymentResponse> {
        val response = checkOutVerifiedPaymentService.checkOut(memberId, request)
        return ResponseEntity.ok(response)
    }
}

package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.MakeCardPaymentService
import com.minseoklim.toyproject2024.payment.dto.MakeCardPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.MakeCardPaymentResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MakeCardPaymentController(
    private val makeCardPaymentService: MakeCardPaymentService
) {
    @PostMapping("/card-payments")
    fun make(
        @MemberId memberId: Int,
        @Valid @RequestBody request: MakeCardPaymentRequest
    ): ResponseEntity<MakeCardPaymentResponse> {
        val response = makeCardPaymentService.make(memberId, request)
        return ResponseEntity.ok(response)
    }
}

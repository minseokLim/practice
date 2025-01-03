package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.MakeCardPaymentService
import com.minseoklim.toyproject2024.payment.dto.ui.MakeCardPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.ui.MakeCardPaymentResponse
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
        @MemberId memberId: Long,
        @Valid @RequestBody request: MakeCardPaymentRequest
    ): ResponseEntity<MakeCardPaymentResponse> {
        val output = makeCardPaymentService.make(memberId, request.toInput())
        return ResponseEntity.ok(MakeCardPaymentResponse.from(output))
    }
}

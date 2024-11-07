package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.CancelPaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelPaymentController(
    private val cancelPaymentService: CancelPaymentService
) {
    @PostMapping("/cancel-payments/{paymentId}")
    fun cancel(
        @MemberId memberId: Long,
        @PathVariable paymentId: Long
    ): ResponseEntity<Unit> {
        cancelPaymentService.cancel(memberId, paymentId)
        return ResponseEntity.ok().build()
    }
}

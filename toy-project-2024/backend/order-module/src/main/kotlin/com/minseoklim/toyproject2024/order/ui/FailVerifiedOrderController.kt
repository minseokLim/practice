package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.FailVerifiedOrderService
import com.minseoklim.toyproject2024.order.dto.ui.FailVerifiedOrderRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class FailVerifiedOrderController(
    private val failVerifiedOrderService: FailVerifiedOrderService
) {
    @PostMapping("/fail-verified-order")
    fun fail(
        @MemberId memberId: Long,
        @Valid @RequestBody request: FailVerifiedOrderRequest
    ): ResponseEntity<Unit> {
        failVerifiedOrderService.fail(memberId, request.orderId)
        return ResponseEntity.ok().build()
    }
}

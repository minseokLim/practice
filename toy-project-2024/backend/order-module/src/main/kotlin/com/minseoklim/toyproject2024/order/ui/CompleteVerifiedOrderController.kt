package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.CompleteVerifiedOrderService
import com.minseoklim.toyproject2024.order.dto.ui.CompleteVerifiedOrderRequest
import com.minseoklim.toyproject2024.order.dto.ui.CompleteVerifiedOrderResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompleteVerifiedOrderController(
    private val completeVerifiedOrderService: CompleteVerifiedOrderService
) {
    @PostMapping("/complete-verified-order")
    fun complete(
        @MemberId memberId: Int,
        @Valid @RequestBody request: CompleteVerifiedOrderRequest
    ): ResponseEntity<CompleteVerifiedOrderResponse> {
        val output = completeVerifiedOrderService.complete(memberId, request.orderId)
        return ResponseEntity.ok(CompleteVerifiedOrderResponse.from(output))
    }
}

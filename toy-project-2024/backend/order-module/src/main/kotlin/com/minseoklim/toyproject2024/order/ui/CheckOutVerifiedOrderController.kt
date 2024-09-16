package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.CheckOutVerifiedOrderService
import com.minseoklim.toyproject2024.order.dto.ui.CheckOutVerifiedOrderRequest
import com.minseoklim.toyproject2024.order.dto.ui.CheckOutVerifiedOrderResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckOutVerifiedOrderController(
    private val checkOutVerifiedOrderService: CheckOutVerifiedOrderService
) {
    @PostMapping("/verified-orders")
    fun checkOut(
        @MemberId memberId: Int,
        @Valid @RequestBody request: CheckOutVerifiedOrderRequest
    ): ResponseEntity<CheckOutVerifiedOrderResponse> {
        val output = checkOutVerifiedOrderService.checkOut(memberId, request.toInput())
        return ResponseEntity.ok(CheckOutVerifiedOrderResponse.of(output))
    }
}

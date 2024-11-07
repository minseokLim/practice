package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.CancelOrderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CancelOrderController(
    private val cancelOrderService: CancelOrderService
) {
    @PostMapping("/cancel-orders/{orderId}")
    fun cancel(
        @MemberId memberId: Long,
        @PathVariable orderId: Long
    ): ResponseEntity<Unit> {
        cancelOrderService.cancel(memberId, orderId)
        return ResponseEntity.ok().build()
    }
}

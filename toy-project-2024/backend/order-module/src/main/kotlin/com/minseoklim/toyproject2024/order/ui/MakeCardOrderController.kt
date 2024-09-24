package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.MakeCardOrderService
import com.minseoklim.toyproject2024.order.dto.ui.MakeCardOrderRequest
import com.minseoklim.toyproject2024.order.dto.ui.MakeCardOrderResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MakeCardOrderController(
    private val makeCardOrderService: MakeCardOrderService
) {
    @PostMapping("/card-orders")
    fun order(
        @MemberId memberId: Int,
        @Valid @RequestBody request: MakeCardOrderRequest
    ): ResponseEntity<MakeCardOrderResponse> {
        val output = makeCardOrderService.order(memberId, request.toInput())
        return ResponseEntity.ok(MakeCardOrderResponse.from(output))
    }
}

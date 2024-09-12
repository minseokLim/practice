package com.minseoklim.toyproject2024.order.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.order.application.QueryOrderService
import com.minseoklim.toyproject2024.order.dto.ui.QueryOrderResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryOrderController(
    private val queryOrderService: QueryOrderService
) {
    @GetMapping("/orders")
    fun list(@MemberId memberId: Int, pageable: Pageable): ResponseEntity<Page<QueryOrderResponse>> {
        val outputs = queryOrderService.list(memberId, pageable)
        return ResponseEntity.ok(outputs.map { QueryOrderResponse.of(it) })
    }
}

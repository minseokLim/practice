package com.minseoklim.toyproject2024.payment.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.payment.application.QueryPaymentService
import com.minseoklim.toyproject2024.payment.dto.ui.QueryPaymentResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class QueryPaymentController(
    private val queryPaymentService: QueryPaymentService
) {
    @GetMapping("/payments")
    fun list(
        @MemberId memberId: Int,
        pageable: Pageable
    ): ResponseEntity<Page<QueryPaymentResponse>> {
        val outputs = queryPaymentService.list(memberId, pageable)
        return ResponseEntity.ok(outputs.map { QueryPaymentResponse.of(it) })
    }
}

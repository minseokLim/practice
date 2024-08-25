package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.QueryProductService
import com.minseoklim.toyproject2024.product.dto.QueryProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class QueryProductController(
    private val queryProductService: QueryProductService
) {
    @GetMapping
    fun list(@MemberId memberId: Int, pageable: Pageable): ResponseEntity<Page<QueryProductResponse>> {
        val responses = queryProductService.list(memberId, pageable)
        return ResponseEntity.ok(responses)
    }
}

package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.RemoveStockQuantityService
import com.minseoklim.toyproject2024.product.dto.ui.RemoveStockQuantityRequest
import com.minseoklim.toyproject2024.product.dto.ui.RemoveStockQuantityResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class RemoveStockQuantityController(
    private val removeStockQuantityService: RemoveStockQuantityService
) {
    @PostMapping("/{productId}/remove-stock-quantity")
    fun removeStockQuantity(
        @MemberId memberId: Int,
        @PathVariable productId: Int,
        @Valid @RequestBody request: RemoveStockQuantityRequest
    ): ResponseEntity<RemoveStockQuantityResponse> {
        val response = removeStockQuantityService.removeStockQuantity(memberId, productId, request.toInput())
        return ResponseEntity.ok(RemoveStockQuantityResponse.of(response))
    }
}

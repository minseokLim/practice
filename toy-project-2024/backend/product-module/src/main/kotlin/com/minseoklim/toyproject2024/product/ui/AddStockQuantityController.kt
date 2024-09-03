package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.AddStockQuantityService
import com.minseoklim.toyproject2024.product.dto.ui.AddStockQuantityRequest
import com.minseoklim.toyproject2024.product.dto.ui.AddStockQuantityResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class AddStockQuantityController(
    private val addStockQuantityService: AddStockQuantityService
) {
    @PostMapping("/{productId}/add-stock-quantity")
    fun addStockQuantity(
        @MemberId memberId: Int,
        @PathVariable productId: Int,
        @Valid @RequestBody request: AddStockQuantityRequest
    ): ResponseEntity<AddStockQuantityResponse> {
        val output = addStockQuantityService.addStockQuantity(memberId, productId, request.toInput())
        return ResponseEntity.ok(AddStockQuantityResponse.of(output))
    }
}

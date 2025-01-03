package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.UpdateProductService
import com.minseoklim.toyproject2024.product.dto.ui.UpdateProductRequest
import com.minseoklim.toyproject2024.product.dto.ui.UpdateProductResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class UpdateProductController(
    private val updateProductService: UpdateProductService
) {
    @PutMapping("/{productId}")
    fun update(
        @MemberId memberId: Long,
        @PathVariable productId: Long,
        @Valid @RequestBody request: UpdateProductRequest
    ): ResponseEntity<UpdateProductResponse> {
        val output = updateProductService.update(memberId, productId, request.toInput())
        return ResponseEntity.ok(UpdateProductResponse.from(output))
    }
}

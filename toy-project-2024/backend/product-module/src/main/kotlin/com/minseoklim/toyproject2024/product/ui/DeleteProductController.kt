package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.DeleteProductService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/products")
class DeleteProductController(
    private val deleteProductService: DeleteProductService
) {
    @DeleteMapping("/{productId}")
    fun delete(
        @MemberId memberId: Int,
        @PathVariable productId: Int
    ): ResponseEntity<Unit> {
        deleteProductService.delete(memberId, productId)
        return ResponseEntity.noContent().build()
    }
}

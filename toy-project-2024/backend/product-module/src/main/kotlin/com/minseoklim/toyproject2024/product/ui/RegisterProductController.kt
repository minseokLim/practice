package com.minseoklim.toyproject2024.product.ui

import com.minseoklim.toyproject2024.common.annotation.MemberId
import com.minseoklim.toyproject2024.product.application.RegisterProductService
import com.minseoklim.toyproject2024.product.dto.ui.RegisterProductRequest
import com.minseoklim.toyproject2024.product.dto.ui.RegisterProductResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("/products")
class RegisterProductController(
    private val registerProductService: RegisterProductService
) {
    @PostMapping
    fun register(
        @MemberId memberId: Int,
        @Valid @RequestBody request: RegisterProductRequest
    ): ResponseEntity<RegisterProductResponse> {
        val response = registerProductService.register(memberId, request.toInput())
        val uri = URI.create("/products/${response.id}")
        return ResponseEntity.created(uri).body(RegisterProductResponse.of(response))
    }
}

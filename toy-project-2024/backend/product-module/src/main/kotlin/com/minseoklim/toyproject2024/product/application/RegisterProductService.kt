package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.RegisterProductRequest
import com.minseoklim.toyproject2024.product.dto.RegisterProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterProductService(
    private val productRepository: ProductRepository
) {
    fun register(memberId: Int, request: RegisterProductRequest): RegisterProductResponse {
        val product = productRepository.save(request.toEntity(memberId))
        return RegisterProductResponse.of(product)
    }
}

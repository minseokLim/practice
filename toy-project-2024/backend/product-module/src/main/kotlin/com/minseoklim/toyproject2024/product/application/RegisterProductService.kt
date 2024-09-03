package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.application.RegisterProductInput
import com.minseoklim.toyproject2024.product.dto.application.RegisterProductOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterProductService(
    private val productRepository: ProductRepository
) {
    fun register(memberId: Int, input: RegisterProductInput): RegisterProductOutput {
        val product = productRepository.save(input.toEntity(memberId))
        return RegisterProductOutput.of(product)
    }
}

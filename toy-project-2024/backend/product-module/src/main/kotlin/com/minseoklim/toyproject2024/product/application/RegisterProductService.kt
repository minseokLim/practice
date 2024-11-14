package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.application.dto.RegisterProductInput
import com.minseoklim.toyproject2024.product.application.dto.RegisterProductOutput
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RegisterProductService(
    private val productRepository: ProductRepository
) {
    fun register(
        memberId: Long,
        input: RegisterProductInput
    ): RegisterProductOutput {
        val product = productRepository.save(input.toEntity(memberId))
        return RegisterProductOutput.from(product)
    }
}

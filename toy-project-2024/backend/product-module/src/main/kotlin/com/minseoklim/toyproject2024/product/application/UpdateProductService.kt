package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.application.dto.UpdateProductInput
import com.minseoklim.toyproject2024.product.application.dto.UpdateProductOutput
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateProductService(
    private val productRepository: ProductRepository
) {
    fun update(
        memberId: Long,
        productId: Long,
        input: UpdateProductInput
    ): UpdateProductOutput {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        product.update(input.toEntity(product))
        return UpdateProductOutput.from(product)
    }
}

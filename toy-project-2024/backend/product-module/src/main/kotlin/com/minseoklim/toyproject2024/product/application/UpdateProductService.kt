package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.application.UpdateProductInput
import com.minseoklim.toyproject2024.product.dto.application.UpdateProductOutput
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

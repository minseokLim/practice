package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteProductService(
    private val productRepository: ProductRepository
) {
    fun delete(
        memberId: Int,
        productId: Int
    ) {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        productRepository.delete(product)
    }
}

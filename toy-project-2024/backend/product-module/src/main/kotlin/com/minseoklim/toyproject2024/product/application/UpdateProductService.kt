package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.UpdateProductRequest
import com.minseoklim.toyproject2024.product.dto.UpdateProductResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateProductService(
    private val productRepository: ProductRepository
) {
    fun update(memberId: Int, productId: Int, request: UpdateProductRequest): UpdateProductResponse {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        product.update(request.toEntity(product))
        return UpdateProductResponse.of(product)
    }
}

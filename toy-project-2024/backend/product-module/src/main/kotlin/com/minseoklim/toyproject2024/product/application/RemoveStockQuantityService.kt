package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.RemoveStockQuantityRequest
import com.minseoklim.toyproject2024.product.dto.RemoveStockQuantityResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemoveStockQuantityService(
    private val productRepository: ProductRepository
) {
    fun removeStockQuantity(
        memberId: Int,
        productId: Int,
        request: RemoveStockQuantityRequest
    ): RemoveStockQuantityResponse {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        product.removeStockQuantity(request.decrement)
        return RemoveStockQuantityResponse.of(product)
    }
}

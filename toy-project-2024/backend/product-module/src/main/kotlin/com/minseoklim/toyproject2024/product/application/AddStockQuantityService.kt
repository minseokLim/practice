package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.AddStockQuantityRequest
import com.minseoklim.toyproject2024.product.dto.AddStockQuantityResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddStockQuantityService(
    private val productRepository: ProductRepository
) {
    fun addStockQuantity(memberId: Int, productId: Int, request: AddStockQuantityRequest): AddStockQuantityResponse {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        product.addStockQuantity(request.increment)
        return AddStockQuantityResponse.of(product)
    }
}

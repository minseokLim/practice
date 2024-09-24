package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityInput
import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemoveStockQuantityService(
    private val productRepository: ProductRepository
) {
    fun removeStockQuantity(
        memberId: Int? = null,
        productId: Int,
        input: RemoveStockQuantityInput
    ): RemoveStockQuantityOutput {
        val product = ProductServiceHelper.getProductForUpdate(productRepository, productId)
        memberId?.let { product.checkAuthority(memberId) }
        product.removeStockQuantity(input.decrement)
        return RemoveStockQuantityOutput.from(product)
    }
}

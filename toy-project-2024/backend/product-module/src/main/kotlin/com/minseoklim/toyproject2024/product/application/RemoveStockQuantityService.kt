package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.application.dto.RemoveStockQuantityInput
import com.minseoklim.toyproject2024.product.application.dto.RemoveStockQuantityOutput
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class RemoveStockQuantityService(
    private val productRepository: ProductRepository
) {
    fun removeStockQuantity(
        memberId: Long? = null,
        productId: Long,
        input: RemoveStockQuantityInput
    ): RemoveStockQuantityOutput {
        val product = ProductServiceHelper.getProductForUpdate(productRepository, productId)
        memberId?.let { product.checkAuthority(memberId) }
        product.removeStockQuantity(input.decrement)
        return RemoveStockQuantityOutput.from(product)
    }
}

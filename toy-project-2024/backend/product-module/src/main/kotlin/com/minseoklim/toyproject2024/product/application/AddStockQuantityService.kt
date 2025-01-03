package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.application.AddStockQuantityInput
import com.minseoklim.toyproject2024.product.dto.application.AddStockQuantityOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddStockQuantityService(
    private val productRepository: ProductRepository
) {
    fun addStockQuantity(
        memberId: Long? = null,
        productId: Long,
        input: AddStockQuantityInput
    ): AddStockQuantityOutput {
        val product = ProductServiceHelper.getProductForUpdate(productRepository, productId)
        memberId?.let { product.checkAuthority(memberId) }
        product.addStockQuantity(input.increment)
        return AddStockQuantityOutput.from(product)
    }
}

package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.product.domain.model.Product
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository

object ProductServiceHelper {
    fun getProduct(
        productRepository: ProductRepository,
        productId: Int
    ): Product {
        return productRepository.findById(productId)
            .orElseThrow { NotFoundException("PRODUCT_NOT_FOUND", "찾을 수 없는 상품입니다.") }
    }
}

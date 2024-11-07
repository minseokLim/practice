package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.product.domain.model.Product
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository

object ProductServiceHelper {
    fun getProduct(
        productRepository: ProductRepository,
        productId: Long
    ): Product {
        return productRepository.findById(productId)
            .orElseThrow { NotFoundException(ErrorCode.PRODUCT_NOT_FOUND) }
    }

    fun getProductForUpdate(
        productRepository: ProductRepository,
        productId: Long
    ): Product {
        return productRepository.findByIdForUpdate(productId)
            ?: throw NotFoundException(ErrorCode.PRODUCT_NOT_FOUND)
    }
}

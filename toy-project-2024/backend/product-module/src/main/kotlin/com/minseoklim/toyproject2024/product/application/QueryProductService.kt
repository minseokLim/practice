package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.application.QueryProductOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryProductService(
    private val productRepository: ProductRepository
) {
    fun list(
        memberId: Int,
        pageable: Pageable
    ): Page<QueryProductOutput> {
        val products = productRepository.findAllByMemberId(memberId, pageable)
        return products.map { QueryProductOutput.of(it) }
    }

    fun get(
        memberId: Int,
        productId: Int
    ): QueryProductOutput {
        val product = ProductServiceHelper.getProduct(productRepository, productId)
        product.checkAuthority(memberId)
        return QueryProductOutput.of(product)
    }
}

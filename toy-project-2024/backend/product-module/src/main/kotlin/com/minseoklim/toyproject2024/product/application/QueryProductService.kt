package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import com.minseoklim.toyproject2024.product.dto.QueryProductResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryProductService(
    private val productRepository: ProductRepository
) {
    fun list(memberId: Int, pageable: Pageable): Page<QueryProductResponse> {
        val products = productRepository.findAllByMemberId(memberId, pageable)
        return products.map { QueryProductResponse.of(it) }
    }
}

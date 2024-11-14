package com.minseoklim.toyproject2024.product.application

import com.minseoklim.toyproject2024.product.application.dto.QueryProductOutput
import com.minseoklim.toyproject2024.product.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class PessimisticQueryProductService(
    private val productRepository: ProductRepository
) {
    fun findAllByIds(ids: Collection<Long>): List<QueryProductOutput> {
        return productRepository.findAllByIdIn(ids).map { QueryProductOutput.from(it) }
    }
}

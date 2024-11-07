package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.order.dto.application.QueryOrderOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryOrderService(
    private val orderRepository: OrderRepository
) {
    fun list(
        memberId: Long,
        pageable: Pageable
    ): Page<QueryOrderOutput> {
        return orderRepository.findAllByMemberId(memberId, pageable).map { QueryOrderOutput.from(it) }
    }

    fun get(
        memberId: Long,
        orderId: Long
    ): QueryOrderOutput {
        val order = OrderServiceHelper.getOrder(orderRepository, orderId)
        order.checkAuthority(memberId)
        return QueryOrderOutput.from(order)
    }
}

package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.order.domain.model.Order
import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository

object OrderServiceHelper {
    fun getOrder(orderRepository: OrderRepository, orderId: Int): Order {
        return orderRepository.findById(orderId)
            .orElseThrow { throw NotFoundException("ORDER_NOT_FOUND", "찾을 수 없는 주문 정보입니다.") }
    }
}

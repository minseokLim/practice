package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.payment.application.CancelPaymentService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CancelOrderService(
    private val orderRepository: OrderRepository,
    private val cancelPaymentService: CancelPaymentService
) {
    fun cancel(
        memberId: Int,
        orderId: Int
    ) {
        val order = OrderServiceHelper.getOrder(orderRepository, orderId)
        order.checkAuthority(memberId)
        order.cancel()

        if (!order.isPaymentWaiting) {
            cancelPaymentService.cancel(memberId, checkNotNull(order.paymentId))
        }
    }
}

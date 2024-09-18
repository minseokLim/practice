package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.order.dto.application.CompleteVerifiedOrderOutput
import com.minseoklim.toyproject2024.payment.application.CompleteVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompleteVerifiedOrderService(
    private val orderRepository: OrderRepository,
    private val completeVerifiedPaymentService: CompleteVerifiedPaymentService
) {
    fun complete(
        memberId: Int,
        orderId: Int
    ): CompleteVerifiedOrderOutput {
        val order = OrderServiceHelper.getOrder(orderRepository, orderId)
        order.checkAuthority(memberId)
        order.changeToPreparing()

        val payment = completeVerifiedPaymentService.completeVerifiedPayment(
            memberId,
            CompleteVerifiedPaymentInput(checkNotNull(order.paymentId))
        )

        return CompleteVerifiedOrderOutput(payment.status)
    }
}

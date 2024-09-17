package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.payment.application.FailVerifiedPaymentService
import com.minseoklim.toyproject2024.payment.dto.application.FailVerifiedPaymentInput
import com.minseoklim.toyproject2024.product.application.AddStockQuantityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FailVerifiedOrderService(
    private val orderRepository: OrderRepository,
    private val failVerifiedPaymentService: FailVerifiedPaymentService,
    private val addStockQuantityService: AddStockQuantityService
) {
    fun fail(
        memberId: Int,
        orderId: Int
    ) {
        val order = OrderServiceHelper.getOrder(orderRepository, orderId)
        order.checkAuthority(memberId)

        failVerifiedPaymentService.failVerifiedPayment(
            memberId,
            FailVerifiedPaymentInput(checkNotNull(order.paymentId))
        )

        OrderServiceHelper.recoverStockQuantity(addStockQuantityService, order.orderProducts)
    }
}

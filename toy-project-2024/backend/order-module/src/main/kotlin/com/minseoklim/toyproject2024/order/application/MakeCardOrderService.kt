package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.order.application.converter.ProductConverter
import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.order.domain.service.OrderNameGenerator
import com.minseoklim.toyproject2024.order.domain.service.TotalAmountCalculator
import com.minseoklim.toyproject2024.order.dto.application.MakeCardOrderInput
import com.minseoklim.toyproject2024.order.dto.application.MakeCardOrderOutput
import com.minseoklim.toyproject2024.payment.application.MakeCardPaymentService
import com.minseoklim.toyproject2024.payment.dto.application.MakeCardPaymentInput
import com.minseoklim.toyproject2024.product.application.PessimisticQueryProductService
import com.minseoklim.toyproject2024.product.application.RemoveStockQuantityService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MakeCardOrderService(
    private val pessimisticQueryProductService: PessimisticQueryProductService,
    private val removeStockQuantityService: RemoveStockQuantityService,
    private val orderRepository: OrderRepository,
    private val makeCardPaymentService: MakeCardPaymentService
) {
    fun order(
        memberId: Long,
        input: MakeCardOrderInput
    ): MakeCardOrderOutput {
        val productIds = input.orderProducts.map { it.productId }
        val products = pessimisticQueryProductService.findAllByIds(productIds).map { ProductConverter.from(it) }

        val orderName = OrderNameGenerator.generate(products)
        val order = orderRepository.save(input.toEntity(memberId = memberId, orderName = orderName))

        OrderServiceHelper.removeStockQuantity(removeStockQuantityService, order.orderProducts)

        val totalAmount = TotalAmountCalculator.calculate(order.orderProducts, products)
        val payment = makeCardPaymentService.make(memberId, MakeCardPaymentInput(input.cardId, totalAmount, orderName))

        order.applyPaymentId(payment.id)
        order.changeToPreparing()

        return MakeCardOrderOutput.from(order)
    }
}

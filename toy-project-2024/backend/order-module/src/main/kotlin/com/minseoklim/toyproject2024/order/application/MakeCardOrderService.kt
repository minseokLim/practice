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
import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityInput
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
        memberId: Int,
        input: MakeCardOrderInput
    ): MakeCardOrderOutput {
        val productIds = input.orderProducts.map { it.productId }
        val products = pessimisticQueryProductService.findAllByIds(productIds).map { ProductConverter.of(it) }
        removeStockQuantity(input.orderProducts)

        val orderName = OrderNameGenerator.generate(products)
        val order = orderRepository.save(input.toEntity(memberId = memberId, orderName = orderName))

        val totalAmount = TotalAmountCalculator.calculate(order.orderProducts, products)
        val payment = makeCardPaymentService.make(memberId, MakeCardPaymentInput(input.cardId, totalAmount, orderName))

        order.applyPayment(payment.id)
        return MakeCardOrderOutput.of(order)
    }

    private fun removeStockQuantity(orderProducts: List<MakeCardOrderInput.OrderProductInput>) {
        orderProducts.forEach {
            removeStockQuantityService.removeStockQuantity(
                productId = it.productId,
                input = RemoveStockQuantityInput(it.quantity)
            )
        }
    }
}

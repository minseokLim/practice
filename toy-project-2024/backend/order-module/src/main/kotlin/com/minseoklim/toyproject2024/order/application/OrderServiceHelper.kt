package com.minseoklim.toyproject2024.order.application

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.order.domain.model.Order
import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.repository.OrderRepository
import com.minseoklim.toyproject2024.product.application.AddStockQuantityService
import com.minseoklim.toyproject2024.product.application.RemoveStockQuantityService
import com.minseoklim.toyproject2024.product.dto.application.AddStockQuantityInput
import com.minseoklim.toyproject2024.product.dto.application.RemoveStockQuantityInput

object OrderServiceHelper {
    fun getOrder(
        orderRepository: OrderRepository,
        orderId: Int
    ): Order {
        return orderRepository.findById(orderId)
            .orElseThrow { throw NotFoundException(ErrorCode.ORDER_NOT_FOUND) }
    }

    fun removeStockQuantity(
        removeStockQuantityService: RemoveStockQuantityService,
        orderProducts: List<OrderProduct>
    ) {
        orderProducts.forEach {
            removeStockQuantityService.removeStockQuantity(
                productId = it.productId,
                input = RemoveStockQuantityInput(it.quantity)
            )
        }
    }

    fun recoverStockQuantity(
        addStockQuantityService: AddStockQuantityService,
        orderProducts: List<OrderProduct>
    ) {
        orderProducts.forEach {
            addStockQuantityService.addStockQuantity(
                productId = it.productId,
                input = AddStockQuantityInput(it.quantity)
            )
        }
    }
}

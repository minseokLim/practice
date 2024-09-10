package com.minseoklim.toyproject2024.order.dto.application

import com.minseoklim.toyproject2024.order.domain.model.Address
import com.minseoklim.toyproject2024.order.domain.model.Order
import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.model.Receiver
import com.minseoklim.toyproject2024.order.domain.model.ShippingInfo

data class MakeCardOrderInput(
    val orderProducts: List<OrderProductInput>,
    val shippingInfo: ShippingInfoInput,
    val cardId: Int
) {
    fun toEntity(memberId: Int, paymentId: Int? = null, orderName: String): Order {
        return Order(
            orderName = orderName,
            orderProducts = orderProducts.map { it.toEmbeddable() },
            shippingInfo = shippingInfo.toEmbeddable(),
            paymentId = paymentId,
            memberId = memberId
        )
    }

    data class OrderProductInput(
        val productId: Int,
        val quantity: Int
    ) {
        fun toEmbeddable(): OrderProduct {
            return OrderProduct(
                productId = productId,
                quantity = quantity
            )
        }
    }

    data class ShippingInfoInput(
        val shippingMessage: String?,
        val address: AddressInput,
        val receiver: ReceiverInput
    ) {
        fun toEmbeddable(): ShippingInfo {
            return ShippingInfo(
                shippingMessage = shippingMessage,
                address = address.toEmbeddable(),
                receiver = receiver.toEmbeddable()
            )
        }
    }

    data class AddressInput(
        val value: String,
        val detail: String,
        val zipCode: String
    ) {
        fun toEmbeddable(): Address {
            return Address(
                value = value,
                detail = detail,
                zipCode = zipCode
            )
        }
    }

    data class ReceiverInput(
        val name: String,
        val phone: String
    ) {
        fun toEmbeddable(): Receiver {
            return Receiver(
                name = name,
                phone = phone
            )
        }
    }
}

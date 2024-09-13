package com.minseoklim.toyproject2024.order.dto.application

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.order.domain.model.Address
import com.minseoklim.toyproject2024.order.domain.model.Order
import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.model.Receiver
import com.minseoklim.toyproject2024.order.domain.model.ShippingInfo

data class QueryOrderOutput(
    val id: Int,
    val orderProducts: List<OrderProductOutput>,
    val shippingInfo: ShippingInfoOutput
) {
    companion object {
        fun of(order: Order): QueryOrderOutput {
            return QueryOrderOutput(
                id = checkNotNull(order.id),
                orderProducts = order.orderProducts.map { OrderProductOutput.of(it) },
                shippingInfo = ShippingInfoOutput.of(order.shippingInfo)
            )
        }
    }

    data class OrderProductOutput private constructor(
        val productId: Int,
        val quantity: Int
    ) {
        companion object {
            fun of(orderProduct: OrderProduct): OrderProductOutput {
                return OrderProductOutput(
                    productId = orderProduct.productId,
                    quantity = orderProduct.quantity
                )
            }
        }
    }

    data class ShippingInfoOutput private constructor(
        val shippingMessage: String?,
        val address: AddressOutput,
        val receiver: ReceiverOutput
    ) {
        companion object {
            fun of(shippingInfo: ShippingInfo): ShippingInfoOutput {
                return ShippingInfoOutput(
                    shippingMessage = shippingInfo.shippingMessage?.value,
                    address = AddressOutput.of(shippingInfo.address),
                    receiver = ReceiverOutput.of(shippingInfo.receiver)
                )
            }
        }
    }

    data class AddressOutput private constructor(
        val value: String,
        val detail: String,
        val zipCode: String
    ) {
        companion object {
            fun of(address: Address): AddressOutput {
                return AddressOutput(
                    value = TextEncryptUtil.decrypt(address.encryptedValue),
                    detail = TextEncryptUtil.decrypt(address.encryptedDetail),
                    zipCode = TextEncryptUtil.decrypt(address.encryptedZipCode)
                )
            }
        }
    }

    data class ReceiverOutput private constructor(
        val name: String,
        val phone: String
    ) {
        companion object {
            fun of(receiver: Receiver): ReceiverOutput {
                return ReceiverOutput(
                    name = TextEncryptUtil.decrypt(receiver.encryptedName),
                    phone = TextEncryptUtil.decrypt(receiver.encryptedPhone)
                )
            }
        }
    }
}

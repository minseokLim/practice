package com.minseoklim.toyproject2024.order.dto.application

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.order.domain.model.Address
import com.minseoklim.toyproject2024.order.domain.model.Order
import com.minseoklim.toyproject2024.order.domain.model.OrderProduct
import com.minseoklim.toyproject2024.order.domain.model.Receiver
import com.minseoklim.toyproject2024.order.domain.model.ShippingInfo

data class MakeCardOrderOutput private constructor(
    val id: Long,
    val orderProducts: List<OrderProductOutput>,
    val shippingInfo: ShippingInfoOutput
) {
    companion object {
        fun from(order: Order): MakeCardOrderOutput {
            return MakeCardOrderOutput(
                id = checkNotNull(order.id),
                orderProducts = order.orderProducts.map { OrderProductOutput.from(it) },
                shippingInfo = ShippingInfoOutput.from(order.shippingInfo)
            )
        }
    }

    data class OrderProductOutput private constructor(
        val productId: Long,
        val quantity: Int
    ) {
        companion object {
            fun from(orderProduct: OrderProduct): OrderProductOutput {
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
            fun from(shippingInfo: ShippingInfo): ShippingInfoOutput {
                return ShippingInfoOutput(
                    shippingMessage = shippingInfo.shippingMessage?.value,
                    address = AddressOutput.from(shippingInfo.address),
                    receiver = ReceiverOutput.from(shippingInfo.receiver)
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
            fun from(address: Address): AddressOutput {
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
            fun from(receiver: Receiver): ReceiverOutput {
                return ReceiverOutput(
                    name = TextEncryptUtil.decrypt(receiver.encryptedName),
                    phone = TextEncryptUtil.decrypt(receiver.encryptedPhone)
                )
            }
        }
    }
}

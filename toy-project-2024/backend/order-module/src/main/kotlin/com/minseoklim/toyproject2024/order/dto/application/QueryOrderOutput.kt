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
            return with(order) {
                QueryOrderOutput(
                    id = checkNotNull(id),
                    orderProducts = orderProducts.map { OrderProductOutput.of(it) },
                    shippingInfo = ShippingInfoOutput.of(shippingInfo)
                )
            }
        }
    }

    data class OrderProductOutput private constructor(
        val productId: Int,
        val quantity: Int
    ) {
        companion object {
            fun of(orderProduct: OrderProduct): OrderProductOutput {
                return with(orderProduct) {
                    OrderProductOutput(
                        productId = productId,
                        quantity = quantity
                    )
                }
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
                return with(shippingInfo) {
                    ShippingInfoOutput(
                        shippingMessage = shippingMessage?.value,
                        address = AddressOutput.of(address),
                        receiver = ReceiverOutput.of(receiver)
                    )
                }
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
                return with(address) {
                    AddressOutput(
                        value = TextEncryptUtil.decrypt(encryptedValue),
                        detail = TextEncryptUtil.decrypt(encryptedDetail),
                        zipCode = TextEncryptUtil.decrypt(encryptedZipCode)
                    )
                }
            }
        }
    }

    data class ReceiverOutput private constructor(
        val name: String,
        val phone: String
    ) {
        companion object {
            fun of(receiver: Receiver): ReceiverOutput {
                return with(receiver) {
                    ReceiverOutput(
                        name = TextEncryptUtil.decrypt(encryptedName),
                        phone = TextEncryptUtil.decrypt(encryptedPhone)
                    )
                }
            }
        }
    }
}

package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.dto.application.QueryOrderOutput

data class QueryOrderResponse private constructor(
    val id: Int,
    val orderProducts: List<OrderProductResponse>,
    val shippingInfo: ShippingInfoResponse
) {
    companion object {
        fun of(output: QueryOrderOutput): QueryOrderResponse {
            return QueryOrderResponse(
                id = output.id,
                orderProducts = output.orderProducts.map { OrderProductResponse.of(it) },
                shippingInfo = ShippingInfoResponse.of(output.shippingInfo)
            )
        }
    }

    data class OrderProductResponse private constructor(
        val productId: Int,
        val quantity: Int
    ) {
        companion object {
            fun of(output: QueryOrderOutput.OrderProductOutput): OrderProductResponse {
                return OrderProductResponse(
                    productId = output.productId,
                    quantity = output.quantity
                )
            }
        }
    }

    data class ShippingInfoResponse private constructor(
        val shippingMessage: String?,
        val address: AddressResponse,
        val receiver: ReceiverResponse
    ) {
        companion object {
            fun of(output: QueryOrderOutput.ShippingInfoOutput): ShippingInfoResponse {
                return ShippingInfoResponse(
                    shippingMessage = output.shippingMessage,
                    address = AddressResponse.of(output.address),
                    receiver = ReceiverResponse.of(output.receiver)
                )
            }
        }
    }

    data class AddressResponse private constructor(
        val value: String,
        val detail: String,
        val zipCode: String
    ) {
        companion object {
            fun of(output: QueryOrderOutput.AddressOutput): AddressResponse {
                return AddressResponse(
                    value = output.value,
                    detail = output.detail,
                    zipCode = output.zipCode
                )
            }
        }
    }

    data class ReceiverResponse private constructor(
        val name: String,
        val phone: String
    ) {
        companion object {
            fun of(output: QueryOrderOutput.ReceiverOutput): ReceiverResponse {
                return ReceiverResponse(
                    name = output.name,
                    phone = output.phone
                )
            }
        }
    }
}

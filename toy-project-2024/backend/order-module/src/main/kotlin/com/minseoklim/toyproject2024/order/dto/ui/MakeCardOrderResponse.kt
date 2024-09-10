package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.dto.application.MakeCardOrderOutput

data class MakeCardOrderResponse private constructor(
    val id: Int,
    val orderProducts: List<OrderProductResponse>,
    val shippingInfo: ShippingInfoResponse
) {
    companion object {
        fun of(output: MakeCardOrderOutput): MakeCardOrderResponse {
            return with(output) {
                MakeCardOrderResponse(
                    id = id,
                    orderProducts = orderProducts.map { OrderProductResponse.of(it) },
                    shippingInfo = ShippingInfoResponse.of(shippingInfo)
                )
            }
        }
    }

    data class OrderProductResponse private constructor(
        val productId: Int,
        val quantity: Int
    ) {
        companion object {
            fun of(output: MakeCardOrderOutput.OrderProductOutput): OrderProductResponse {
                return with(output) {
                    OrderProductResponse(
                        productId = productId,
                        quantity = quantity
                    )
                }
            }
        }
    }

    data class ShippingInfoResponse private constructor(
        val shippingMessage: String?,
        val address: AddressResponse,
        val receiver: ReceiverResponse
    ) {
        companion object {
            fun of(output: MakeCardOrderOutput.ShippingInfoOutput): ShippingInfoResponse {
                return with(output) {
                    ShippingInfoResponse(
                        shippingMessage = shippingMessage,
                        address = AddressResponse.of(address),
                        receiver = ReceiverResponse.of(receiver)
                    )
                }
            }
        }
    }

    data class AddressResponse private constructor(
        val value: String,
        val detail: String,
        val zipCode: String
    ) {
        companion object {
            fun of(output: MakeCardOrderOutput.AddressOutput): AddressResponse {
                return with(output) {
                    AddressResponse(
                        value = value,
                        detail = detail,
                        zipCode = zipCode
                    )
                }
            }
        }
    }

    data class ReceiverResponse private constructor(
        val name: String,
        val phone: String
    ) {
        companion object {
            fun of(output: MakeCardOrderOutput.ReceiverOutput): ReceiverResponse {
                return with(output) {
                    ReceiverResponse(
                        name = name,
                        phone = phone
                    )
                }
            }
        }
    }
}

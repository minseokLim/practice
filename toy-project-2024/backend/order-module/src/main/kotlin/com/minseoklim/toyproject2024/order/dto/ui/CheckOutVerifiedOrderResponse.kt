package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.dto.application.CheckOutVerifiedOrderOutput

data class CheckOutVerifiedOrderResponse private constructor(
    val id: Int,
    val orderName: String,
    val payment: PaymentResponse,
    val orderProducts: List<OrderProductResponse>,
    val shippingInfo: ShippingInfoResponse
) {
    companion object {
        fun from(output: CheckOutVerifiedOrderOutput): CheckOutVerifiedOrderResponse {
            return CheckOutVerifiedOrderResponse(
                id = output.id,
                orderName = output.orderName,
                payment = PaymentResponse.from(output.payment),
                orderProducts = output.orderProducts.map { OrderProductResponse.from(it) },
                shippingInfo = ShippingInfoResponse.from(output.shippingInfo)
            )
        }
    }

    data class PaymentResponse private constructor(
        val id: Int,
        val amount: Long,
        val paymentUid: String
    ) {
        companion object {
            fun from(output: CheckOutVerifiedOrderOutput.PaymentOutput): PaymentResponse {
                return PaymentResponse(
                    id = output.id,
                    amount = output.amount,
                    paymentUid = output.paymentUid
                )
            }
        }
    }

    data class OrderProductResponse private constructor(
        val productId: Int,
        val quantity: Int
    ) {
        companion object {
            fun from(output: CheckOutVerifiedOrderOutput.OrderProductOutput): OrderProductResponse {
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
            fun from(output: CheckOutVerifiedOrderOutput.ShippingInfoOutput): ShippingInfoResponse {
                return ShippingInfoResponse(
                    shippingMessage = output.shippingMessage,
                    address = AddressResponse.from(output.address),
                    receiver = ReceiverResponse.from(output.receiver)
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
            fun from(output: CheckOutVerifiedOrderOutput.AddressOutput): AddressResponse {
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
            fun from(output: CheckOutVerifiedOrderOutput.ReceiverOutput): ReceiverResponse {
                return ReceiverResponse(
                    name = output.name,
                    phone = output.phone
                )
            }
        }
    }
}

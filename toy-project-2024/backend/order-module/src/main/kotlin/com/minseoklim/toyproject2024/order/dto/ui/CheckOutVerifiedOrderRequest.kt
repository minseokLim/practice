package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.domain.model.Address
import com.minseoklim.toyproject2024.order.domain.model.Receiver
import com.minseoklim.toyproject2024.order.dto.application.CheckOutVerifiedOrderInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class CheckOutVerifiedOrderRequest(
    val orderProducts: List<OrderProductRequest>,
    val shippingInfo: ShippingInfoRequest
) {
    fun toInput(): CheckOutVerifiedOrderInput {
        return CheckOutVerifiedOrderInput(
            orderProducts = orderProducts.map { it.toInput() },
            shippingInfo = shippingInfo.toInput()
        )
    }

    data class OrderProductRequest(
        val productId: Int,
        val quantity: Int
    ) {
        fun toInput(): CheckOutVerifiedOrderInput.OrderProductInput {
            return CheckOutVerifiedOrderInput.OrderProductInput(
                productId = productId,
                quantity = quantity
            )
        }
    }

    data class ShippingInfoRequest(
        val shippingMessage: String?,
        val address: AddressRequest,
        val receiver: ReceiverRequest
    ) {
        fun toInput(): CheckOutVerifiedOrderInput.ShippingInfoInput {
            return CheckOutVerifiedOrderInput.ShippingInfoInput(
                shippingMessage = shippingMessage,
                address = address.toInput(),
                receiver = receiver.toInput()
            )
        }
    }

    data class AddressRequest(
        @get:NotBlank(message = Address.ERR_MSG)
        val value: String,

        @get:NotBlank(message = Address.ERR_MSG_DETAIL)
        val detail: String,

        @get:Pattern(regexp = Address.REGEX_STR_ZIP_CODE, message = Address.ERR_MSG_ZIP_CODE)
        val zipCode: String
    ) {
        fun toInput(): CheckOutVerifiedOrderInput.AddressInput {
            return CheckOutVerifiedOrderInput.AddressInput(
                value = value,
                detail = detail,
                zipCode = zipCode
            )
        }
    }

    data class ReceiverRequest(
        @get:Pattern(regexp = Receiver.REGEX_STR_NAME, message = Receiver.ERR_MSG_NAME)
        val name: String,

        @get:Pattern(regexp = Receiver.REGEX_STR_PHONE, message = Receiver.ERR_MSG_PHONE)
        val phone: String
    ) {
        fun toInput(): CheckOutVerifiedOrderInput.ReceiverInput {
            return CheckOutVerifiedOrderInput.ReceiverInput(
                name = name,
                phone = phone
            )
        }
    }
}

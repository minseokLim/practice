package com.minseoklim.toyproject2024.order.dto.ui

import com.minseoklim.toyproject2024.order.domain.model.Address
import com.minseoklim.toyproject2024.order.domain.model.Receiver
import com.minseoklim.toyproject2024.order.dto.application.MakeCardOrderInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class MakeCardOrderRequest(
    val orderProducts: List<OrderProductRequest>,
    val shippingInfo: ShippingInfoRequest,
    val cardId: Long
) {
    fun toInput(): MakeCardOrderInput {
        return MakeCardOrderInput(
            orderProducts = orderProducts.map { it.toInput() },
            shippingInfo = shippingInfo.toInput(),
            cardId = cardId
        )
    }

    data class OrderProductRequest(
        val productId: Long,
        val quantity: Int
    ) {
        fun toInput(): MakeCardOrderInput.OrderProductInput {
            return MakeCardOrderInput.OrderProductInput(
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
        fun toInput(): MakeCardOrderInput.ShippingInfoInput {
            return MakeCardOrderInput.ShippingInfoInput(
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
        fun toInput(): MakeCardOrderInput.AddressInput {
            return MakeCardOrderInput.AddressInput(
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
        fun toInput(): MakeCardOrderInput.ReceiverInput {
            return MakeCardOrderInput.ReceiverInput(
                name = name,
                phone = phone
            )
        }
    }
}

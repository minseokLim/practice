package com.minseoklim.toyproject2024.order.domain.model

import jakarta.persistence.Embeddable

@Embeddable
class ShippingInfo(
    shippingMessage: String?,
    address: Address,
    receiver: Receiver
) {
    val shippingMessage: ShippingMessage? = shippingMessage?.let { ShippingMessage(it) }

    val address: Address = address

    val receiver: Receiver = receiver
}

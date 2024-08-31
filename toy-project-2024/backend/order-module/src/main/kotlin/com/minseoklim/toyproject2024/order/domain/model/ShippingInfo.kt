package com.minseoklim.toyproject2024.order.domain.model

import jakarta.persistence.Embeddable

@Embeddable
class ShippingInfo(
    shippingMessage: String?,
    address: String,
    addressDetail: String,
    zipCode: String,
    receiverName: String,
    receiverPhone: String
) {
    val shippingMessage: ShippingMessage? = shippingMessage?.let { ShippingMessage(it) }

    val address: Address = Address(address, addressDetail, zipCode)

    val receiver: Receiver = Receiver(receiverName, receiverPhone)
}

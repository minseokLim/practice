package com.minseoklim.toyproject2024.payment.application

interface CardPaymentApi {
    fun requestPayment(request: CardPaymentRequest)

    fun cancelPayment(request: CardPaymentCancelRequest)

    data class CardPaymentRequest(
        val paymentUid: String,
        val amount: Long,
        val cardNumber: String,
        val cardExpiry: String,
        val birth: String,
        val pwd2digit: String,
        val productName: String
    )

    data class CardPaymentCancelRequest(
        val paymentUid: String,
        val amount: Long
    )
}

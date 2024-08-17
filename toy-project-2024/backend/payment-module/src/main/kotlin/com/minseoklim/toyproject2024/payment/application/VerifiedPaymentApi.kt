package com.minseoklim.toyproject2024.payment.application

interface VerifiedPaymentApi {
    fun getVerifiedPaymentAmount(paymentUid: String): Long
    fun cancelPayment(paymentUid: String)
}

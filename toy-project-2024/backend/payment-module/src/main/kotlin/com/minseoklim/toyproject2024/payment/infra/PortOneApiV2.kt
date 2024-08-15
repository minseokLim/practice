package com.minseoklim.toyproject2024.payment.infra

import com.minseoklim.toyproject2024.payment.application.VerifiedPaymentApi
import com.minseoklim.toyproject2024.payment.config.PortOneProperty
import org.springframework.stereotype.Component

@Component
class PortOneApiV2(
    private val portOneProperty: PortOneProperty,
    private val portOneClientV2: PortOneClientV2
) : VerifiedPaymentApi {
    override fun getVerifiedPaymentAmount(paymentUid: String): Long {
        val response = portOneClientV2.getPayment("PortOne ${portOneProperty.secretV2}", paymentUid)
        return response.amount.total
    }
}
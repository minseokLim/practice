package com.minseoklim.toyproject2024.payment.infra

import com.minseoklim.toyproject2024.payment.application.VerifiedPaymentApi
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class DummyVerifiedPaymentApi : VerifiedPaymentApi {
    override fun getVerifiedPaymentAmount(paymentUid: String): Long {
        return 2000L
    }

    override fun cancelPayment(paymentUid: String) {
        // do nothing
    }
}

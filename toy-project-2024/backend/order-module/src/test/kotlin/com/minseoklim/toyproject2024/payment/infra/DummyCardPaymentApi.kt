package com.minseoklim.toyproject2024.payment.infra

import com.minseoklim.toyproject2024.payment.application.CardPaymentApi
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class DummyCardPaymentApi : CardPaymentApi {
    override fun requestPayment(request: CardPaymentApi.CardPaymentRequest) {
        // do nothing
    }

    override fun cancelPayment(request: CardPaymentApi.CardPaymentCancelRequest) {
        // do nothing
    }
}

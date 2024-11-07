package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.application.CardPaymentApi.CardPaymentCancelRequest
import com.minseoklim.toyproject2024.payment.domain.model.CardPayment
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CancelPaymentService(
    private val paymentRepository: PaymentRepository,
    private val cardPaymentApi: CardPaymentApi,
    private val verifiedPaymentApi: VerifiedPaymentApi
) {
    fun cancel(
        memberId: Long,
        paymentId: Long
    ) {
        val payment = PaymentServiceHelper.getPayment(paymentRepository, paymentId)
        payment.checkAuthority(memberId)
        payment.cancel()

        when (payment) {
            is CardPayment -> cardPaymentApi.cancelPayment(
                CardPaymentCancelRequest(
                    paymentUid = payment.paymentUid.value,
                    amount = payment.amount.value.toLong()
                )
            )

            is VerifiedPayment -> verifiedPaymentApi.cancelPayment(payment.paymentUid.value)
        }
    }
}

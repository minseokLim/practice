package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentInput
import com.minseoklim.toyproject2024.payment.dto.application.CompleteVerifiedPaymentOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompleteVerifiedPaymentService(
    private val paymentRepository: PaymentRepository,
    private val verifiedPaymentApi: VerifiedPaymentApi
) {
    fun completeVerifiedPayment(
        memberId: Int,
        input: CompleteVerifiedPaymentInput
    ): CompleteVerifiedPaymentOutput {
        val payment = PaymentServiceHelper.getVerifiedPayment(paymentRepository, input.paymentUid)
        payment.checkAuthority(memberId)

        val paidAmount = verifiedPaymentApi.getVerifiedPaymentAmount(input.paymentUid)
        if (payment.hasAmount(paidAmount)) {
            payment.complete()
        } else {
            payment.tamper()
        }

        return CompleteVerifiedPaymentOutput(payment.status)
    }
}

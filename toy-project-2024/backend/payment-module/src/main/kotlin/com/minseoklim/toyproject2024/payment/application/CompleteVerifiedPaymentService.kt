package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.CompleteVerifiedPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.CompleteVerifiedPaymentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CompleteVerifiedPaymentService(
    private val paymentRepository: PaymentRepository,
    private val verifiedPaymentApi: VerifiedPaymentApi
) {
    fun completeVerifiedPayment(request: CompleteVerifiedPaymentRequest): CompleteVerifiedPaymentResponse {
        val payment = PaymentServiceHelper.getVerifiedPayment(paymentRepository, request.paymentUid)
        val paidAmount = verifiedPaymentApi.getVerifiedPaymentAmount(request.paymentUid)
        if (payment.hasAmount(paidAmount)) {
            payment.complete()
        } else {
            payment.tamper()
        }

        return CompleteVerifiedPaymentResponse(payment.status)
    }
}

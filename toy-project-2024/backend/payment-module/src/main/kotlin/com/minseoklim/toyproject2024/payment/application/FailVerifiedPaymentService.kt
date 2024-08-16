package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.FailVerifiedPaymentRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FailVerifiedPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun failVerifiedPayment(request: FailVerifiedPaymentRequest) {
        val payment = PaymentServiceHelper.getVerifiedPayment(paymentRepository, request.paymentUid)
        payment.fail()
    }
}

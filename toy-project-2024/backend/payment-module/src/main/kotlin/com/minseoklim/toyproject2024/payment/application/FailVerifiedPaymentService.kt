package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.FailVerifiedPaymentInput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class FailVerifiedPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun failVerifiedPayment(
        memberId: Long,
        input: FailVerifiedPaymentInput
    ) {
        val payment = PaymentServiceHelper.getVerifiedPayment(paymentRepository, input.paymentId)
        payment.checkAuthority(memberId)
        payment.fail()
    }
}

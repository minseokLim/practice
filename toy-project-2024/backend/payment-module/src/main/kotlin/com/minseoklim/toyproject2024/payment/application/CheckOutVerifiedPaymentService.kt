package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.CheckOutVerifiedPaymentInput
import com.minseoklim.toyproject2024.payment.dto.application.CheckOutVerifiedPaymentOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CheckOutVerifiedPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun checkOut(
        memberId: Int,
        input: CheckOutVerifiedPaymentInput
    ): CheckOutVerifiedPaymentOutput {
        val verifiedPayment = paymentRepository.save(input.toEntity(memberId))
        return CheckOutVerifiedPaymentOutput.from(verifiedPayment)
    }
}

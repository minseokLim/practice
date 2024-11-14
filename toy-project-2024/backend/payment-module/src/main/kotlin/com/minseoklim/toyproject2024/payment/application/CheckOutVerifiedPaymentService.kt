package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.application.dto.CheckOutVerifiedPaymentInput
import com.minseoklim.toyproject2024.payment.application.dto.CheckOutVerifiedPaymentOutput
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CheckOutVerifiedPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun checkOut(
        memberId: Long,
        input: CheckOutVerifiedPaymentInput
    ): CheckOutVerifiedPaymentOutput {
        val verifiedPayment = paymentRepository.save(input.toEntity(memberId))
        return CheckOutVerifiedPaymentOutput.from(verifiedPayment)
    }
}

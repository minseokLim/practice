package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.CheckOutVerifiedPaymentRequest
import com.minseoklim.toyproject2024.payment.dto.CheckOutVerifiedPaymentResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CheckOutVerifiedPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun checkOut(memberId: Int, request: CheckOutVerifiedPaymentRequest): CheckOutVerifiedPaymentResponse {
        val verifiedPayment = paymentRepository.save(request.toEntity(memberId))
        return CheckOutVerifiedPaymentResponse.of(verifiedPayment)
    }
}

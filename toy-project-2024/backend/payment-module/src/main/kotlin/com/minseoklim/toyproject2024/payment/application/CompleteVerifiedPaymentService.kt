package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
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
        val payment = paymentRepository.findByPaymentUidValue(request.paymentUid)
            ?: throw NotFoundException("PAYMENT_NOT_FOUND", "찾을 수 없는 결제 정보입니다.")
        if (payment !is VerifiedPayment) {
            throw BadRequestException("NOT_VERIFIED_PAYMENT", "인증 결제 정보가 아닙니다.")
        }

        val paidAmount = verifiedPaymentApi.getVerifiedPaymentAmount(request.paymentUid)
        if (paidAmount == payment.amount.value.toLong()) {
            payment.complete()
        } else {
            payment.tamper()
        }

        return CompleteVerifiedPaymentResponse(payment.status)
    }
}

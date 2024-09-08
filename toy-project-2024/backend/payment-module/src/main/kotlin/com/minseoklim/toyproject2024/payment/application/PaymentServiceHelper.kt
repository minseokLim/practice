package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.payment.domain.model.Payment
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository

object PaymentServiceHelper {
    fun getPayment(paymentRepository: PaymentRepository, paymentId: Int): Payment {
        return paymentRepository.findById(paymentId)
            .orElseThrow { throw NotFoundException("PAYMENT_NOT_FOUND", "찾을 수 없는 결제 정보입니다.") }
    }

    fun getVerifiedPayment(paymentRepository: PaymentRepository, paymentId: Int): VerifiedPayment {
        val payment = getPayment(paymentRepository, paymentId)
        if (payment !is VerifiedPayment) {
            throw BadRequestException("NOT_VERIFIED_PAYMENT", "인증 결제 정보가 아닙니다.")
        }
        return payment
    }
}

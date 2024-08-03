package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.payment.domain.model.Payment
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository

object PaymentServiceHelper {
    fun getPayment(paymentRepository: PaymentRepository, paymentId: Int): Payment {
        return paymentRepository.findById(paymentId)
            .orElseThrow { throw NotFoundException("PAYMENT_NOT_FOUND", "찾을 수 없는 결제 정보입니다.") }
    }
}

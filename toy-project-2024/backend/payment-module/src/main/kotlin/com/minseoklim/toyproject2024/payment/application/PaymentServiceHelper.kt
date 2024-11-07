package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.payment.domain.model.Payment
import com.minseoklim.toyproject2024.payment.domain.model.VerifiedPayment
import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository

object PaymentServiceHelper {
    fun getPayment(
        paymentRepository: PaymentRepository,
        paymentId: Long
    ): Payment {
        return paymentRepository.findById(paymentId)
            .orElseThrow { throw NotFoundException(ErrorCode.PAYMENT_NOT_FOUND) }
    }

    fun getVerifiedPayment(
        paymentRepository: PaymentRepository,
        paymentId: Long
    ): VerifiedPayment {
        val payment = getPayment(paymentRepository, paymentId)
        if (payment !is VerifiedPayment) {
            throw BadRequestException(ErrorCode.NOT_VERIFIED_PAYMENT)
        }
        return payment
    }
}

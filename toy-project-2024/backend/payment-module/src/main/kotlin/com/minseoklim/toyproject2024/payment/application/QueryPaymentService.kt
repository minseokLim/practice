package com.minseoklim.toyproject2024.payment.application

import com.minseoklim.toyproject2024.payment.domain.repository.PaymentRepository
import com.minseoklim.toyproject2024.payment.dto.application.QueryPaymentOutput
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QueryPaymentService(
    private val paymentRepository: PaymentRepository
) {
    fun list(
        memberId: Int,
        pageable: Pageable
    ): Page<QueryPaymentOutput> {
        return paymentRepository.findAllByMemberIdWithVerifiedCompleted(memberId, pageable)
            .map { QueryPaymentOutput.from(it) }
    }
}

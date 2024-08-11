package com.minseoklim.toyproject2024.payment.domain.repository

import com.minseoklim.toyproject2024.payment.domain.model.Payment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Int> {
    fun findAllByMemberId(memberId: Int, pageable: Pageable): Page<Payment>
    fun findByPaymentUidValue(paymentUid: String): Payment?
}

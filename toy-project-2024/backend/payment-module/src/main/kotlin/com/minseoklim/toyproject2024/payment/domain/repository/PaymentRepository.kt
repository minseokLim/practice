package com.minseoklim.toyproject2024.payment.domain.repository

import com.minseoklim.toyproject2024.payment.domain.model.Payment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PaymentRepository : JpaRepository<Payment, Int> {
    @Query("SELECT p FROM Payment p WHERE p.memberId = :memberId AND (TYPE(p) != VerifiedPayment OR (TYPE(p) = VerifiedPayment AND p.status = VerifiedPaymentStatus.COMPLETED))")
    fun findAllByMemberIdWithVerifiedCompleted(memberId: Int, pageable: Pageable): Page<Payment>
    fun findByPaymentUidValue(paymentUid: String): Payment?
}

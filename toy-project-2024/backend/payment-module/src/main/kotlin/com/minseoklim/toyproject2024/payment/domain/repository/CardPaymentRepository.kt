package com.minseoklim.toyproject2024.payment.domain.repository

import com.minseoklim.toyproject2024.payment.domain.model.CardPayment
import org.springframework.data.jpa.repository.JpaRepository

interface CardPaymentRepository : JpaRepository<CardPayment, Int>

package com.minseoklim.toyproject2024.card.domain.repository

import com.minseoklim.toyproject2024.card.domain.model.Card
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Long> {
    fun findAllByMemberId(
        memberId: Long,
        pageable: Pageable
    ): Page<Card>
}

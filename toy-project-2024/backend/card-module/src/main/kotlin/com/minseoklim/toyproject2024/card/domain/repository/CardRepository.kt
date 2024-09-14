package com.minseoklim.toyproject2024.card.domain.repository

import com.minseoklim.toyproject2024.card.domain.model.Card
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Int> {
    fun findAllByMemberId(
        memberId: Int,
        pageable: Pageable
    ): Page<Card>
}

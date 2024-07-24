package com.minseoklim.toyproject2024.card.domain.repository

import com.minseoklim.toyproject2024.card.domain.model.Card
import org.springframework.data.jpa.repository.JpaRepository

interface CardRepository : JpaRepository<Card, Int>

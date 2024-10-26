package com.minseoklim.toyproject2024.location.domain.repository

import com.minseoklim.toyproject2024.location.domain.model.Location
import org.springframework.data.jpa.repository.JpaRepository

interface LocationRepository : JpaRepository<Location, Int> {
    fun findByMemberId(memberId: Int): Location?
}

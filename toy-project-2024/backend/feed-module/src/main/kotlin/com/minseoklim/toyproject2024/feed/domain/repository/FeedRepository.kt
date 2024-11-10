package com.minseoklim.toyproject2024.feed.domain.repository

import com.minseoklim.toyproject2024.feed.domain.model.Feed
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface FeedRepository : JpaRepository<Feed, Long> {
    fun findAllByIdLessThanOrderByIdDesc(
        cursorId: Long,
        pageable: Pageable
    ): List<Feed>
}

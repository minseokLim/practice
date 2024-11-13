package com.minseoklim.toyproject2024.feed.application

import com.minseoklim.toyproject2024.feed.domain.model.FeedLikeFlagId
import com.minseoklim.toyproject2024.feed.domain.repository.FeedLikeFlagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UncheckFeedLikeFlagService(
    private val feedLikeFlagRepository: FeedLikeFlagRepository
) {
    fun uncheck(
        memberId: Long,
        feedId: Long
    ) {
        feedLikeFlagRepository.deleteById(FeedLikeFlagId(feedId = feedId, memberId = memberId))
    }
}

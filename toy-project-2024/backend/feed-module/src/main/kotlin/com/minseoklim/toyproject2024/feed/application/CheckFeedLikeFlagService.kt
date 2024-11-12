package com.minseoklim.toyproject2024.feed.application

import com.minseoklim.toyproject2024.feed.domain.model.FeedLikeFlag
import com.minseoklim.toyproject2024.feed.domain.repository.FeedLikeFlagRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CheckFeedLikeFlagService(
    private val feedLikeFlagRepository: FeedLikeFlagRepository
) {
    fun check(
        memberId: Long,
        feedId: Long
    ) {
        feedLikeFlagRepository.save(FeedLikeFlag(feedId = feedId, memberId = memberId))
    }
}

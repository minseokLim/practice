package com.minseoklim.toyproject2024.feed.domain.repository

import com.minseoklim.toyproject2024.feed.domain.model.FeedLikeFlag
import com.minseoklim.toyproject2024.feed.domain.model.FeedLikeFlagId
import org.springframework.data.jpa.repository.JpaRepository

interface FeedLikeFlagRepository : JpaRepository<FeedLikeFlag, FeedLikeFlagId>

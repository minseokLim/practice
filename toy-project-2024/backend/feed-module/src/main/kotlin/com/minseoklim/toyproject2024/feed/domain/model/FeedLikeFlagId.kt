package com.minseoklim.toyproject2024.feed.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Embeddable
import java.io.Serializable
import java.util.Objects

@Embeddable
class FeedLikeFlagId(
    val feedId: Long,
    val memberId: Long
) : Serializable {
    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.feedId == y.feedId && x.memberId == y.memberId }
    }

    final override fun hashCode(): Int = Objects.hash(feedId, memberId)
}

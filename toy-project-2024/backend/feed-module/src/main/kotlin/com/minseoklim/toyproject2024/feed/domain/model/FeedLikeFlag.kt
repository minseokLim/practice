package com.minseoklim.toyproject2024.feed.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.EmbeddedId
import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.UniqueConstraint

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["feed_id", "member_id"])])
class FeedLikeFlag(
    feedId: Long,
    memberId: Long
) {
    @EmbeddedId
    val id: FeedLikeFlagId = FeedLikeFlagId(feedId = feedId, memberId = memberId)

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}

package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class ChatRoomMember(
    val memberId: Int,
    val lastReadMessageId: Long? = null
) {
    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.memberId == y.memberId && x.lastReadMessageId == y.lastReadMessageId }
    }

    final override fun hashCode(): Int = Objects.hash(memberId, lastReadMessageId)
}

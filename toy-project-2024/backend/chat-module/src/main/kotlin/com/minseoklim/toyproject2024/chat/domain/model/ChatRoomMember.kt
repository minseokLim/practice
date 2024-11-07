package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class ChatRoomMember(
    memberId: Long,
    lastReadMessageId: Long? = null
) {
    val memberId = memberId

    var lastReadMessageId = lastReadMessageId
        protected set

    fun updateLastReadMessageId(messageId: Long) {
        if (this.lastReadMessageId == null || checkNotNull(this.lastReadMessageId) < messageId) {
            this.lastReadMessageId = messageId
        }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.memberId == y.memberId && x.lastReadMessageId == y.lastReadMessageId }
    }

    final override fun hashCode(): Int = Objects.hash(memberId, lastReadMessageId)
}

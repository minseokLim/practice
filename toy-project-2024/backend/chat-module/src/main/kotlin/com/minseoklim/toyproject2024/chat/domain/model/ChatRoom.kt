package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class ChatRoom(
    memberIds: List<Long>,
    creatorId: Long
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val chatRoomMembers: ChatRoomMembers = ChatRoomMembers(memberIds)

    val creatorId: Long = creatorId

    var lastMessageId: Long? = null
        protected set

    fun inviteMember(memberId: Long) {
        chatRoomMembers.addMember(memberId)
    }

    fun leaveMember(memberId: Long) {
        chatRoomMembers.deleteMember(memberId)
    }

    fun getMemberIds(): List<Long> {
        return chatRoomMembers.getMemberIds()
    }

    fun updateLastMessageId(messageId: Long) {
        if (lastMessageId == null || checkNotNull(lastMessageId) < messageId) {
            lastMessageId = messageId
        }
    }

    fun checkAuthority(memberId: Long) {
        if (memberId !in this.getMemberIds()) {
            throw NoPermissionException(ErrorCode.NO_CHAT_ROOM_PERMISSION)
        }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}

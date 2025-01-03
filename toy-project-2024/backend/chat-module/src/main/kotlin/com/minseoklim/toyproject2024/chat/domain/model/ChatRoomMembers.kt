package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.OrderColumn
import jakarta.persistence.UniqueConstraint
import java.util.Objects

@Embeddable
class ChatRoomMembers(
    memberIds: List<Long>
) {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "chat_room_member",
        joinColumns = [JoinColumn(name = "chat_room_id")],
        uniqueConstraints = [UniqueConstraint(columnNames = ["member_id", "chat_room_id"])]
    )
    @OrderColumn(name = "chat_room_member_idx")
    private val values: MutableList<ChatRoomMember> = memberIds.map { ChatRoomMember(it) }.toMutableList()

    fun addMember(memberId: Long) {
        values.add(ChatRoomMember(memberId))
    }

    fun deleteMember(memberId: Long) {
        values.removeIf { it.memberId == memberId }
    }

    fun getChatRoomMembers(): List<ChatRoomMember> {
        return values
    }

    fun getMemberIds(): List<Long> {
        return values.map { it.memberId }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.values == y.values }
    }

    final override fun hashCode(): Int = Objects.hash(values)
}

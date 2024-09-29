package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.Index
import jakarta.persistence.JoinColumn
import jakarta.persistence.OrderColumn
import java.util.Objects

@Embeddable
class ChatRoomMembers(
    memberIds: List<Int>
) {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "chat_room_member",
        joinColumns = [JoinColumn(name = "chat_room_id")],
        indexes = [Index(columnList = "member_id")]
    )
    @OrderColumn(name = "chat_room_member_idx")
    private val values: MutableList<ChatRoomMember> = memberIds.map { ChatRoomMember(it) }.toMutableList()

    fun addMember(memberId: Int) {
        values.add(ChatRoomMember(memberId))
    }

    fun deleteMember(memberId: Int) {
        values.removeIf { it.memberId == memberId }
    }

    fun getMemberIds(): List<Int> {
        return values.map { it.memberId }
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.values == y.values }
    }

    final override fun hashCode(): Int = Objects.hash(values)
}

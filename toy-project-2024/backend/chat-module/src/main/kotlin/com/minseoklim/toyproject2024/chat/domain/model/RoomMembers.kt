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
class RoomMembers(
    memberIds: List<Int>
) {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "room_member",
        joinColumns = [JoinColumn(name = "room_id")],
        indexes = [Index(columnList = "member_id")]
    )
    @OrderColumn(name = "room_member_idx")
    private val values: MutableList<RoomMember> = memberIds.map { RoomMember(it) }.toMutableList()

    fun addMember(memberId: Int) {
        values.add(RoomMember(memberId))
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

package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.domain.BaseTimeEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Room(
    memberIds: List<Int>,
    creatorId: Int
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    val roomMembers: RoomMembers = RoomMembers(memberIds)

    val creatorId: Int = creatorId

    fun inviteMember(memberId: Int) {
        roomMembers.addMember(memberId)
    }

    fun leaveMember(memberId: Int) {
        roomMembers.deleteMember(memberId)
    }

    fun getMemberIds(): List<Int> {
        return roomMembers.getMemberIds()
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id != null && x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}

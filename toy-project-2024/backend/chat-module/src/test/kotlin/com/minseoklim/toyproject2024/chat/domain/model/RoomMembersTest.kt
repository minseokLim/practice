package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoomMembersTest {
    @Test
    fun addMember() {
        // given
        val roomMembers = RoomMembers(listOf(1, 2, 3))

        // when
        roomMembers.addMember(4)

        // then
        assertThat(roomMembers.getMemberIds()).contains(4)
    }

    @Test
    fun deleteMember() {
        // given
        val roomMembers = RoomMembers(listOf(1, 2, 3))

        // when
        roomMembers.deleteMember(2)

        // then
        assertThat(roomMembers.getMemberIds()).doesNotContain(2)
    }

    @Test
    fun getMemberIds() {
        // given
        val roomMembers = RoomMembers(listOf(1, 2, 3))

        // when
        val memberIds = roomMembers.getMemberIds()

        // then
        assertThat(memberIds).contains(1, 2, 3)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val roomMembers1 = RoomMembers(listOf(1, 2, 3))
        val roomMembers2 = RoomMembers(listOf(1, 2, 3))
        val roomMembers3 = RoomMembers(listOf(1, 2, 4))

        // when, then
        assertThat(setOf(roomMembers1, roomMembers2, roomMembers3)).hasSize(2)
    }
}

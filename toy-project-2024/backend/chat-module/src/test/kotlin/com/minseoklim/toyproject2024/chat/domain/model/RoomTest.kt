package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class RoomTest {
    @Test
    fun inviteMember() {
        // given
        val room = Room(listOf(1, 2, 3), 1)

        // when
        room.inviteMember(4)

        // then
        assertThat(room.getMemberIds()).contains(4)
    }

    @Test
    fun leaveMember() {
        // given
        val room = Room(listOf(1, 2, 3), 1)

        // when
        room.leaveMember(2)

        // then
        assertThat(room.getMemberIds()).doesNotContain(2)
    }

    @Test
    fun getMemberIds() {
        // given
        val room = Room(listOf(1, 2, 3), 1)

        // when
        val memberIds = room.getMemberIds()

        // then
        assertThat(memberIds).contains(1, 2, 3)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val room1 = Room(listOf(1, 2, 3), 1)
        val room2 = Room(listOf(1, 2, 3), 1)
        val room3 = Room(listOf(1, 2, 3), 1)
        ReflectionTestUtils.setField(room1, "id", 1L)
        ReflectionTestUtils.setField(room2, "id", 1L)

        // when, then
        assertThat(setOf(room1, room2, room3)).hasSize(2)
    }
}

package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoomMemberTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val roomMember1 = RoomMember(1)
        val roomMember2 = RoomMember(1)
        val roomMember3 = RoomMember(2)

        // when, then
        assertThat(setOf(roomMember1, roomMember2, roomMember3)).hasSize(2)
    }
}

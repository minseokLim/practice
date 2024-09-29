package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class ChatRoomTest {
    @Test
    fun inviteMember() {
        // given
        val chatRoom = ChatRoom(listOf(1, 2, 3), 1)

        // when
        chatRoom.inviteMember(4)

        // then
        assertThat(chatRoom.getMemberIds()).contains(4)
    }

    @Test
    fun leaveMember() {
        // given
        val chatRoom = ChatRoom(listOf(1, 2, 3), 1)

        // when
        chatRoom.leaveMember(2)

        // then
        assertThat(chatRoom.getMemberIds()).doesNotContain(2)
    }

    @Test
    fun getMemberIds() {
        // given
        val chatRoom = ChatRoom(listOf(1, 2, 3), 1)

        // when
        val memberIds = chatRoom.getMemberIds()

        // then
        assertThat(memberIds).contains(1, 2, 3)
    }

    @Test
    fun updateLastMessageId() {
        // given
        val chatRoom = ChatRoom(listOf(1, 2, 3), 1)

        // when
        chatRoom.updateLastMessageId(1L)

        // then
        assertThat(chatRoom.lastMessageId).isEqualTo(1L)

        // when
        chatRoom.updateLastMessageId(2L)

        // then
        assertThat(chatRoom.lastMessageId).isEqualTo(2L)

        // when
        chatRoom.updateLastMessageId(1L)

        // then
        assertThat(chatRoom.lastMessageId).isEqualTo(2L)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val chatRoom1 = ChatRoom(listOf(1, 2, 3), 1)
        val chatRoom2 = ChatRoom(listOf(1, 2, 3), 1)
        val chatRoom3 = ChatRoom(listOf(1, 2, 3), 1)
        ReflectionTestUtils.setField(chatRoom1, "id", 1L)
        ReflectionTestUtils.setField(chatRoom2, "id", 1L)

        // when, then
        assertThat(setOf(chatRoom1, chatRoom2, chatRoom3)).hasSize(2)
    }
}

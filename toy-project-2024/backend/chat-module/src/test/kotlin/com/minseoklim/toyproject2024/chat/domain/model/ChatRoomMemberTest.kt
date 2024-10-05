package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChatRoomMemberTest {
    @Test
    fun updateLastReadMessageId() {
        // given
        val chatRoomMember = ChatRoomMember(1)

        // when
        chatRoomMember.updateLastReadMessageId(1)

        // then
        assertThat(chatRoomMember.lastReadMessageId).isEqualTo(1)

        // when
        chatRoomMember.updateLastReadMessageId(2)

        // then
        assertThat(chatRoomMember.lastReadMessageId).isEqualTo(2)

        // when
        chatRoomMember.updateLastReadMessageId(1)

        // then
        assertThat(chatRoomMember.lastReadMessageId).isEqualTo(2)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val chatRoomMember1 = ChatRoomMember(1, 1)
        val chatRoomMember2 = ChatRoomMember(1, 1)
        val chatRoomMember3 = ChatRoomMember(2, 1)

        // when, then
        assertThat(setOf(chatRoomMember1, chatRoomMember2, chatRoomMember3)).hasSize(2)
    }
}

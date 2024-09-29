package com.minseoklim.toyproject2024.chat.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ChatRoomMembersTest {
    @Test
    fun addMember() {
        // given
        val chatRoomMembers = ChatRoomMembers(listOf(1, 2, 3))

        // when
        chatRoomMembers.addMember(4)

        // then
        assertThat(chatRoomMembers.getMemberIds()).contains(4)
    }

    @Test
    fun deleteMember() {
        // given
        val chatRoomMembers = ChatRoomMembers(listOf(1, 2, 3))

        // when
        chatRoomMembers.deleteMember(2)

        // then
        assertThat(chatRoomMembers.getMemberIds()).doesNotContain(2)
    }

    @Test
    fun getMemberIds() {
        // given
        val chatRoomMembers = ChatRoomMembers(listOf(1, 2, 3))

        // when
        val memberIds = chatRoomMembers.getMemberIds()

        // then
        assertThat(memberIds).contains(1, 2, 3)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val chatRoomMembers1 = ChatRoomMembers(listOf(1, 2, 3))
        val chatRoomMembers2 = ChatRoomMembers(listOf(1, 2, 3))
        val chatRoomMembers3 = ChatRoomMembers(listOf(1, 2, 4))

        // when, then
        assertThat(setOf(chatRoomMembers1, chatRoomMembers2, chatRoomMembers3)).hasSize(2)
    }
}

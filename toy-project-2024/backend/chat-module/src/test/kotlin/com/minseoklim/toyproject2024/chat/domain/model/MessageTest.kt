package com.minseoklim.toyproject2024.chat.domain.model

import com.minseoklim.toyproject2024.common.exception.NoPermissionException
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.util.ReflectionTestUtils

class MessageTest {
    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun checkAuthority() {
        // given
        val message = Message(
            content = "Hello, World!",
            chatRoomId = 1,
            memberId = 1L
        )

        // when, then
        assertThatNoException().isThrownBy { message.checkAuthority(1) }

        // when, then
        assertThatThrownBy {
            message.checkAuthority(2)
        }.isInstanceOf(NoPermissionException::class.java)
    }

    @Test
    fun delete() {
        // given
        val message = Message(
            content = "Hello, World!",
            chatRoomId = 1,
            memberId = 1L
        )

        // when
        message.delete()

        // then
        assertThat(message.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val message1 = Message(
            content = "Hello, World!",
            chatRoomId = 1,
            memberId = 1L
        )
        val message2 = Message(
            content = "Hello, World!",
            chatRoomId = 1,
            memberId = 1L
        )
        val message3 = Message(
            content = "Hello, World!",
            chatRoomId = 1,
            memberId = 2
        )
        ReflectionTestUtils.setField(message1, "id", 1L)
        ReflectionTestUtils.setField(message2, "id", 1L)

        // when, then
        assertThat(setOf(message1, message2, message3)).hasSize(2)
    }
}

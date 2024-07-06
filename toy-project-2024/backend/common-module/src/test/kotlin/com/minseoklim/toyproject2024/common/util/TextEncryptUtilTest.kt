package com.minseoklim.toyproject2024.common.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class TextEncryptUtilTest {

    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.text(PASSWORD, SALT))
    }

    @Test
    fun encrypt() {
        // given
        val input = "test"

        // when
        val encrypted = TextEncryptUtil.encrypt(input)

        // then
        assertThat(encrypted).isNotNull
    }

    @Test
    fun decrypt() {
        // given
        val input = "test"
        val encrypted = TextEncryptUtil.encrypt(input)

        // when
        val decrypted = TextEncryptUtil.decrypt(encrypted)

        // then
        assertThat(decrypted).isEqualTo(input)
    }

    companion object {
        private const val PASSWORD = "70617373776F7264"
        private const val SALT = "73616C74"
    }
}

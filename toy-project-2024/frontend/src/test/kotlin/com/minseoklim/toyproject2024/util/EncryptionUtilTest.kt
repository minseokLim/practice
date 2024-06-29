package com.minseoklim.toyproject2024.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class EncryptionUtilTest {
    private val encryptionUtil = EncryptionUtil(PASSWORD, SALT)

    @Test
    fun encrypt() {
        // given
        val input = "test"

        // when
        val encrypted = encryptionUtil.encrypt(input)

        // then
        assertThat(encrypted).isNotNull
    }

    @Test
    fun decrypt() {
        // given
        val input = "test"
        val encrypted = encryptionUtil.encrypt(input)

        // when
        val decrypted = encryptionUtil.decrypt(encrypted)

        // then
        assertThat(decrypted).isEqualTo(input)
    }

    companion object {
        private const val PASSWORD = "70617373776F7264"
        private const val SALT = "73616C74"
    }
}

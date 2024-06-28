package com.minseoklim.toyproject2024.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AesUtilTest {
    private val aesUtil = AesUtil(SECRET, IV)

    @Test
    fun encrypt() {
        // given
        val input = "test"

        // when
        val encrypted = aesUtil.encrypt(input)

        // then
        assertThat(encrypted).isNotNull
    }

    @Test
    fun decrypt() {
        // given
        val input = "test"
        val encrypted = aesUtil.encrypt(input)

        // when
        val decrypted = aesUtil.decrypt(encrypted)

        // then
        assertThat(decrypted).isEqualTo(input)
    }

    companion object {
        private const val SECRET = "01234567890123456789012345678901"
        private const val IV = "0123456789012345"
    }
}

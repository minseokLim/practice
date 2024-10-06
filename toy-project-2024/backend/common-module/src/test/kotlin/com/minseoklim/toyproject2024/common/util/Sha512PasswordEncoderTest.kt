package com.minseoklim.toyproject2024.common.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class Sha512PasswordEncoderTest {
    private val passwordEncoder = Sha512PasswordEncoder()

    @Test
    fun encode() {
        // given
        val rawPassword = "password"

        // when
        val encoded1 = passwordEncoder.encode(rawPassword)
        val encoded2 = passwordEncoder.encode(rawPassword)

        // then
        assertThat(encoded1).isNotEqualTo(encoded2)
    }

    @Test
    fun matches() {
        // given
        val rawPassword = "password"
        val encoded = passwordEncoder.encode(rawPassword)

        // when
        val result1 = passwordEncoder.matches(rawPassword, encoded)

        // then
        assertThat(result1).isTrue

        // when
        val result2 = passwordEncoder.matches("rawPassword", encoded)

        // then
        assertThat(result2).isFalse
    }
}

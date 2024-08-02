package com.minseoklim.toyproject2024.common.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordEncodeUtilTest {

    @BeforeEach
    fun setUp() {
        PasswordEncodeUtil.init(BCryptPasswordEncoder())
    }

    @Test
    fun encode() {
        // given
        val password = "password"

        // when
        val encodedPassword = PasswordEncodeUtil.encode(password)

        // then
        assertThat(encodedPassword).isNotNull
    }
}

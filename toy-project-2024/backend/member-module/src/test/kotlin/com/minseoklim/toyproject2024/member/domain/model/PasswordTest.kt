package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class PasswordTest {
    @BeforeEach
    fun setUp() {
        PasswordEncodeUtil.init(BCryptPasswordEncoder())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Password("test1234")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Password("")
        }
    }
}

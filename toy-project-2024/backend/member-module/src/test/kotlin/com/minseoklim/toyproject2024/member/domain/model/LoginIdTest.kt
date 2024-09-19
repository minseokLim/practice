package com.minseoklim.toyproject2024.member.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class LoginIdTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            LoginId("test1234")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            LoginId("test1234@")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val loginId1 = LoginId("test1234")
        val loginId2 = LoginId("test1234")
        val loginId3 = LoginId("other1234")

        // when, then
        assertThat(setOf(loginId1, loginId2, loginId3)).hasSize(2)
    }
}

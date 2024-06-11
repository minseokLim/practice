package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.*
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
        val set = hashSetOf<LoginId>()

        // when
        set.add(LoginId("test1234"))
        set.add(LoginId("test1234"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

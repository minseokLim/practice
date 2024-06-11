package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class PasswordTest {

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

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<Password>()

        // when
        set.add(Password("test1234"))
        set.add(Password("test1234"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

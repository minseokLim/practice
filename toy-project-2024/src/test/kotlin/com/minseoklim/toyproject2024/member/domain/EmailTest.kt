package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class EmailTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Email("test@test.com")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Email("test")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<Email>()

        // when
        set.add(Email("test@test.com"))
        set.add(Email("test@test.com"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

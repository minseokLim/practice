package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class NameTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Name("임민석")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Name("임민석@")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<Name>()

        // when
        set.add(Name("임민석"))
        set.add(Name("임민석"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

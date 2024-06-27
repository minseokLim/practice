package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Test

class SocialIdTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            SocialId("test1234")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            SocialId("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<SocialId>()

        // when
        set.add(SocialId("test1234"))
        set.add(SocialId("test1234"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

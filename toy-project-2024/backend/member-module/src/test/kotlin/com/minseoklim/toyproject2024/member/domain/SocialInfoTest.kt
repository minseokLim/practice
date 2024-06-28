package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialInfoTest {

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<SocialInfo>()

        // when
        set.add(SocialInfo(SocialType.GOOGLE, "1234"))
        set.add(SocialInfo(SocialType.GOOGLE, "1234"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

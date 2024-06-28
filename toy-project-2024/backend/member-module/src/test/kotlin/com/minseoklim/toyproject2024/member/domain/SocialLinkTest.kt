package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialLinkTest {

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<SocialLink>()

        // when
        set.add(SocialLink(SocialType.GOOGLE, "1234"))
        set.add(SocialLink(SocialType.GOOGLE, "1234"))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

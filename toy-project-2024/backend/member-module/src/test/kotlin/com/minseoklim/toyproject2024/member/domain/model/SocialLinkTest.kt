package com.minseoklim.toyproject2024.member.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialLinkTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val socialLink1 = SocialLink(SocialType.GOOGLE, "1234")
        val socialLink2 = SocialLink(SocialType.GOOGLE, "1234")
        val socialLink3 = SocialLink(SocialType.KAKAO, "1234")

        // when, then
        assertThat(setOf(socialLink1, socialLink2, socialLink3)).hasSize(2)
    }
}

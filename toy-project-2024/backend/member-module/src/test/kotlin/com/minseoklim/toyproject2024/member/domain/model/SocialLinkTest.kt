package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.junit.jupiter.api.Test

class SocialLinkTest {

    @Test
    fun equalsAndHashCode() {
        // given
        val socialLink1 = SocialLink(SocialType.GOOGLE, "1234")
        val socialLink2 = SocialLink(SocialType.GOOGLE, "1234")
        val socialLink3 = SocialLink(SocialType.KAKAO, "1234")

        // when, then
        TestUtil.testEqualsAndHashCode(socialLink1, socialLink2, socialLink3)
    }
}

package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialInfosTest {

    @Test
    fun addSocialInfo() {
        // given
        val socialInfos = SocialInfos()

        // when
        socialInfos.addSocialInfo(SocialType.GOOGLE, "1234")

        // then
        assertThat(socialInfos.getSocialInfos()).contains(SocialInfo(SocialType.GOOGLE, "1234"))
    }

    @Test
    fun getSocialInfos() {
        // given
        val socialInfos = SocialInfos()
        socialInfos.addSocialInfo(SocialType.GOOGLE, "1234")
        socialInfos.addSocialInfo(SocialType.GOOGLE, "5678")

        // when
        val socialInfoSet = socialInfos.getSocialInfos()

        // then
        assertThat(socialInfoSet).contains(SocialInfo(SocialType.GOOGLE, "1234"), SocialInfo(SocialType.GOOGLE, "5678"))
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<SocialInfos>()

        // when
        set.add(SocialInfos().apply { addSocialInfo(SocialType.GOOGLE, "1234") })
        set.add(SocialInfos().apply { addSocialInfo(SocialType.GOOGLE, "1234") })

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

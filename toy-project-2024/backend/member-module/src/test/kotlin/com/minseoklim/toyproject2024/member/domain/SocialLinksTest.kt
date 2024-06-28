package com.minseoklim.toyproject2024.member.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialLinksTest {

    @Test
    fun addSocialLink() {
        // given
        val socialLinks = SocialLinks()

        // when
        socialLinks.addSocialLink(SocialType.GOOGLE, "1234")

        // then
        assertThat(socialLinks.getSocialLinks()).contains(SocialLink(SocialType.GOOGLE, "1234"))
    }

    @Test
    fun getSocialLinks() {
        // given
        val socialLinks = SocialLinks()
        socialLinks.addSocialLink(SocialType.GOOGLE, "1234")
        socialLinks.addSocialLink(SocialType.GOOGLE, "5678")

        // when
        val socialLinkSet = socialLinks.getSocialLinks()

        // then
        assertThat(socialLinkSet).contains(SocialLink(SocialType.GOOGLE, "1234"), SocialLink(SocialType.GOOGLE, "5678"))
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<SocialLinks>()

        // when
        set.add(SocialLinks().apply { addSocialLink(SocialType.GOOGLE, "1234") })
        set.add(SocialLinks().apply { addSocialLink(SocialType.GOOGLE, "1234") })

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

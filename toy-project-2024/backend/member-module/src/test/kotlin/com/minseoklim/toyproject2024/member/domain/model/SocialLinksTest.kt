package com.minseoklim.toyproject2024.member.domain.model

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
    fun deleteSocialLink() {
        // given
        val socialLinks = SocialLinks()
        socialLinks.addSocialLink(SocialType.GOOGLE, "1234")

        // when
        socialLinks.deleteSocialLink(SocialType.GOOGLE)

        // then
        assertThat(socialLinks.getSocialLinks()).isEmpty()
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
        val socialLinks1 = SocialLinks().apply { this.addSocialLink(SocialType.GOOGLE, "1234") }
        val socialLinks2 = SocialLinks().apply { this.addSocialLink(SocialType.GOOGLE, "1234") }
        val socialLinks3 = SocialLinks().apply { this.addSocialLink(SocialType.KAKAO, "1234") }

        // when, then
        assertThat(setOf(socialLinks1, socialLinks2, socialLinks3)).hasSize(2)
    }
}

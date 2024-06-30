package com.minseoklim.toyproject2024.member.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SocialTypeTest {

    @Test
    fun toMemberEntity() {
        // given
        val googleAttributes = mapOf(
            "sub" to "1234",
            "name" to "임민석",
            "email" to "test@test.com"
        )

        // when
        val googleMember = SocialType.GOOGLE.toMemberEntity(googleAttributes)

        // then
        assertThat(googleMember).isNotNull

        // given
        val kakaoAttributes = mapOf(
            "id" to 1234,
            "properties" to mapOf(
                "nickname" to "임민석"
            )
        )

        // when
        val kakaoMember = SocialType.KAKAO.toMemberEntity(kakaoAttributes)

        // then
        assertThat(kakaoMember).isNotNull
    }

    @Test
    fun extractSocialId() {
        // given
        val googleAttributes = mapOf(
            "sub" to "1234",
            "name" to "임민석",
            "email" to "test@test.com"
        )

        // when
        val googleSocialId = SocialType.GOOGLE.extractSocialId(googleAttributes)

        // then
        assertThat(googleSocialId).isEqualTo("1234")

        // given
        val kakaoAttributes = mapOf(
            "id" to 1234,
            "properties" to mapOf(
                "nickname" to "임민석"
            )
        )

        // when
        val kakaoSocialId = SocialType.KAKAO.extractSocialId(kakaoAttributes)

        // then
        assertThat(kakaoSocialId).isEqualTo("1234")
    }
}

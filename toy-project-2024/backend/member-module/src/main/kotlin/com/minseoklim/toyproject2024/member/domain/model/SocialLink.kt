package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.util.Objects

@Embeddable
class SocialLink(
    socialType: SocialType,
    socialId: String
) {
    @Enumerated(EnumType.STRING)
    val socialType: SocialType = socialType
    val socialId: SocialId = SocialId(socialId)

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.socialType == y.socialType && x.socialId == y.socialId }
    }

    final override fun hashCode(): Int = Objects.hash(socialType, socialId)
}

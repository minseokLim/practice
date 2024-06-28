package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class SocialInfo(
    socialType: SocialType,
    socialId: String
) {
    @Enumerated(EnumType.STRING)
    val socialType: SocialType = socialType
    val socialId: SocialId = SocialId(socialId)

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as SocialInfo

        return socialType == other.socialType && socialId == other.socialId
    }

    final override fun hashCode(): Int = Objects.hash(socialType, socialId)
}

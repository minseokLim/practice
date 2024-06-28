package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.UniqueConstraint
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class SocialLinks(
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
        name = "social_info",
        joinColumns = [JoinColumn(name = "member_id")],
        uniqueConstraints = [UniqueConstraint(columnNames = ["social_id", "social_type"])]
    )
    private val values: MutableSet<SocialLink> = mutableSetOf()
) {
    fun addSocialLink(socialType: SocialType, socialId: String) {
        values.add(SocialLink(socialType, socialId))
    }

    fun deleteSocialLink(socialType: SocialType) {
        values.removeIf { it.socialType == socialType }
    }

    fun getSocialLinks(): Set<SocialLink> {
        return values.toSet()
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as SocialLinks

        return values == other.values
    }

    final override fun hashCode(): Int = Objects.hash(values)
}

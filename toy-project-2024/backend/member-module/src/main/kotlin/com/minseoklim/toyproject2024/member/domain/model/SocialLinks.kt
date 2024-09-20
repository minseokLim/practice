package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEmbeddable
import jakarta.persistence.CollectionTable
import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.FetchType
import jakarta.persistence.JoinColumn
import jakarta.persistence.UniqueConstraint
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
    fun addSocialLink(
        socialType: SocialType,
        socialId: String
    ) {
        values.add(SocialLink(socialType, socialId))
    }

    fun deleteSocialLink(socialType: SocialType) {
        values.removeIf { it.socialType == socialType }
    }

    fun getSocialLinks(): Set<SocialLink> {
        return values.toSet()
    }

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEmbeddable(other) { x, y -> x.values == y.values }
    }

    final override fun hashCode(): Int = Objects.hash(values)
}

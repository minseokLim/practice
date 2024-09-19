package com.minseoklim.toyproject2024.auth.domain.model

import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntity
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.hashCodeForEntity
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class LoginHistory(
    val memberId: Int,
    val tokenId: String,
    val clientIp: String,
    val userAgent: String,

    @Enumerated(EnumType.STRING)
    val socialType: SocialType? = null,
    val loginDateTime: LocalDateTime = LocalDateTime.now(),

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null
) {
    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntity(other) { x, y -> x.id == y.id }
    }

    final override fun hashCode(): Int = this.hashCodeForEntity()
}

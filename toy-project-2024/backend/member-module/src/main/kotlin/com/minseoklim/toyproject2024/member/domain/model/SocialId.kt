package com.minseoklim.toyproject2024.member.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class SocialId(
    @Column(name = "social_id")
    val value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as SocialId

        return value == other.value
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val ERR_MSG = "소셜 ID는 공백일 수 없습니다."
    }
}

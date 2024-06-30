package com.minseoklim.toyproject2024.member.domain.model

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class Email(
    @Column(name = "email")
    val value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Email

        return value == other.value
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val REGEX_STR = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$"
        const val ERR_MSG = "이메일 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

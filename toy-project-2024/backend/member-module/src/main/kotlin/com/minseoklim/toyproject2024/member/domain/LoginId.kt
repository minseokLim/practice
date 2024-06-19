package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class LoginId(
    @Column(name = "login_id")
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
        other as LoginId

        return value == other.value
    }

    final override fun hashCode(): Int = Objects.hash(value);

    companion object {
        const val REGEX_STR = "^[a-z0-9]{5,20}\$"
        const val ERR_MSG = "로그인 ID는 5~20자의 영문 소문자, 숫자만 사용 가능합니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

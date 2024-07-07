package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.ConsistentHashUtil
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@Embeddable
class Name(
    value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    @Column(name = "hashed_name")
    val hashedValue: String = ConsistentHashUtil.hash(value)

    @Column(name = "encrypted_name")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    final override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null) return false
        val oEffectiveClass =
            if (other is HibernateProxy) other.hibernateLazyInitializer.persistentClass else other.javaClass
        val thisEffectiveClass =
            if (this is HibernateProxy) this.hibernateLazyInitializer.persistentClass else this.javaClass
        if (thisEffectiveClass != oEffectiveClass) return false
        other as Name

        return hashedValue == other.hashedValue
    }

    final override fun hashCode(): Int = Objects.hash(hashedValue)

    companion object {
        const val REGEX_STR = "^[a-zA-Z가-힇0-9]{2,10}\$"
        const val ERR_MSG = "이름은 2~10자의 영문 대소문자, 한글, 숫자만 사용 가능합니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.ConsistentHashUtil
import com.minseoklim.toyproject2024.common.util.JpaEqualityUtil.equalsForEntityAndEmbeddable
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class Email(
    value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    @Column(name = "hashed_email")
    val hashedValue: String = ConsistentHashUtil.hash(value)

    @Column(name = "encrypted_email")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    final override fun equals(other: Any?): Boolean {
        return this.equalsForEntityAndEmbeddable(other) { x, y -> x.hashedValue == y.hashedValue }
    }

    final override fun hashCode(): Int = Objects.hash(hashedValue)

    companion object {
        const val REGEX_STR = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$"
        const val ERR_MSG = "이메일 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Email(
    @Column(name = "email")
    val value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Email

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        const val REGEX_STR = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+\$"
        const val ERR_MSG = "이메일 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

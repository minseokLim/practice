package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class LoginId(
    @Column(name = "login_id")
    val value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginId

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        const val REGEX_STR = "^[a-z0-9]{5,20}\$"
        const val ERR_MSG = "로그인 ID는 5~20자의 영문 소문자, 숫자만 사용 가능합니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

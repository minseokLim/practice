package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Name(
    @Column(name = "name")
    val value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Name

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        const val REGEX_STR = "^[a-zA-Z가-힇0-9]{2,10}\$"
        const val ERR_MSG = "이름은 2~10자의 영문 대소문자, 한글, 숫자만 사용 가능합니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

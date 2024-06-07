package com.minseoklim.toyproject2024.member.domain

import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Password(
    @Column(name = "password")
    val value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Password

        return value == other.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

    companion object {
        const val ERR_MSG = "비밀번호는 공백일 수 없습니다."
    }
}

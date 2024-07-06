package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Password(
    value: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
    }

    @Column(name = "password")
    val value: String = PasswordEncodeUtil.encodePassword(value)

    companion object {
        const val ERR_MSG = "비밀번호는 공백일 수 없습니다."
    }
}

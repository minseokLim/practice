package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

// encryptedValue는 암호화할 때마다 변하기 때문에 equals, hashCode를 오버라이드하지 않음
@Embeddable
class Pwd2digit(
    value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    @Column(name = "encrypted_password_2digit")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    companion object {
        const val REGEX_STR = "^\\d{2}\$"
        const val ERR_MSG = "비밀번호 2자리 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

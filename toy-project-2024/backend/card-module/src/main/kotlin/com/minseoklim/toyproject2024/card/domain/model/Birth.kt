package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

// encryptedValue는 암호화할 때마다 변하기 때문에 equals, hashCode를 오버라이드하지 않음
@Embeddable
class Birth(
    value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    @Column(name = "encrypted_birth")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    companion object {
        const val REGEX_STR = "^\\d{2}(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])\$"
        const val ERR_MSG = "생년월일 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

// maskedValue가 같다고 해서 같은 값이라고 할 수 없고 encryptedValue는 암호화할 때마다 변하기 때문에 equals, hashCode를 오버라이드하지 않음
@Embeddable
class CardNumber(
    value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    @Column(name = "masked_card_number")
    val maskedValue: String = value.toMaskedCardNumber()

    @Column(name = "encrypted_card_number")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    private fun String.toMaskedCardNumber(): String {
        val parts = this.split("-")
        return "${parts[0]}-****-****-${parts[3]}"
    }

    companion object {
        const val REGEX_STR = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}\$"
        const val ERR_MSG = "카드 번호 형식이 올바르지 않습니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Receiver(
    name: String,
    phone: String
) {
    init {
        require(REGEX_NAME.matches(name)) { ERR_MSG_NAME }
        require(REGEX_PHONE.matches(phone)) { ERR_MSG_PHONE }
    }

    @Column(name = "encrypted_receiver_name")
    val encryptedName: String = TextEncryptUtil.encrypt(name)

    @Column(name = "encrypted_receiver_phone")
    val encryptedPhone: String = TextEncryptUtil.encrypt(phone)

    companion object {
        const val REGEX_STR_NAME = "^[a-zA-Z가-힇0-9]{2,10}\$"
        const val ERR_MSG_NAME = "수령인 이름은 2~10자의 영문 대소문자, 한글, 숫자만 사용 가능합니다."
        private val REGEX_NAME = REGEX_STR_NAME.toRegex()

        const val REGEX_STR_PHONE = "^\\d{3}-\\d{3,4}-\\d{4}\$"
        const val ERR_MSG_PHONE = "수령인 전화번호 형식이 올바르지 않습니다."
        private val REGEX_PHONE = REGEX_STR_PHONE.toRegex()
    }
}

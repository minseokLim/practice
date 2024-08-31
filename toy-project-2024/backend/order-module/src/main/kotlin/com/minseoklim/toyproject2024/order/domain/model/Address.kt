package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import jakarta.persistence.Column
import jakarta.persistence.Embeddable

@Embeddable
class Address(
    value: String,
    detail: String,
    zipCode: String
) {
    init {
        require(value.isNotBlank()) { ERR_MSG }
        require(detail.isNotBlank()) { ERR_MSG_DETAIL }
        require(REGEX_ZIP_CODE.matches(zipCode)) { ERR_MSG_ZIP_CODE }
    }

    @Column(name = "encrypted_address")
    val encryptedValue: String = TextEncryptUtil.encrypt(value)

    @Column(name = "encrypted_address_detail")
    val encryptedDetail: String = TextEncryptUtil.encrypt(detail)

    @Column(name = "encrypted_zip_code")
    val encryptedZipCode: String = TextEncryptUtil.encrypt(zipCode)

    companion object {
        const val ERR_MSG = "주소는 공백일 수 없습니다."
        const val ERR_MSG_DETAIL = "상세 주소는 공백일 수 없습니다."

        const val REGEX_STR_ZIP_CODE = "^\\d{5}\$"
        const val ERR_MSG_ZIP_CODE = "우편번호 형식이 올바르지 않습니다."
        private val REGEX_ZIP_CODE = REGEX_STR_ZIP_CODE.toRegex()
    }
}

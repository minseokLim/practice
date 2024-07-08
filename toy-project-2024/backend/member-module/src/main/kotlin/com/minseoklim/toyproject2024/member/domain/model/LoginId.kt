package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.CommonUtil.entityEmbeddableEquals
import jakarta.persistence.Column
import jakarta.persistence.Embeddable
import java.util.Objects

@Embeddable
class LoginId(
    @Column(name = "login_id")
    val value: String
) {
    init {
        require(REGEX.matches(value)) { ERR_MSG }
    }

    final override fun equals(other: Any?): Boolean {
        return this.entityEmbeddableEquals(other) { x, y -> x.value == y.value }
    }

    final override fun hashCode(): Int = Objects.hash(value)

    companion object {
        const val REGEX_STR = "^[a-z0-9]{5,20}\$"
        const val ERR_MSG = "로그인 ID는 5~20자의 영문 소문자, 숫자만 사용 가능합니다."
        private val REGEX = REGEX_STR.toRegex()
    }
}

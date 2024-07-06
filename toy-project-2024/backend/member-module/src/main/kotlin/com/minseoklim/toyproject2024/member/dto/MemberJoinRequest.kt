package com.minseoklim.toyproject2024.member.dto

import com.minseoklim.toyproject2024.member.domain.model.Email
import com.minseoklim.toyproject2024.member.domain.model.LoginId
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Name
import com.minseoklim.toyproject2024.member.domain.model.Password
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class MemberJoinRequest(
    @get:Pattern(regexp = LoginId.REGEX_STR, message = LoginId.ERR_MSG)
    val loginId: String,

    @get:NotBlank(message = Password.ERR_MSG)
    val password: String,

    @get:Pattern(regexp = Name.REGEX_STR, message = Name.ERR_MSG)
    val name: String,

    @get:Pattern(regexp = Email.REGEX_STR, message = Email.ERR_MSG)
    val email: String
) {
    fun toEntity(): Member {
        return Member(loginId, password, name, email)
    }
}

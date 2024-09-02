package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.domain.model.Email
import com.minseoklim.toyproject2024.member.domain.model.LoginId
import com.minseoklim.toyproject2024.member.domain.model.Name
import com.minseoklim.toyproject2024.member.domain.model.Password
import com.minseoklim.toyproject2024.member.dto.application.JoinMemberInput
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class JoinMemberRequest(
    @get:Pattern(regexp = LoginId.REGEX_STR, message = LoginId.ERR_MSG)
    val loginId: String,

    @get:NotBlank(message = Password.ERR_MSG)
    val password: String,

    @get:Pattern(regexp = Name.REGEX_STR, message = Name.ERR_MSG)
    val name: String,

    @get:Pattern(regexp = Email.REGEX_STR, message = Email.ERR_MSG)
    val email: String
) {
    fun toInput(): JoinMemberInput {
        return JoinMemberInput(
            loginId = loginId,
            password = password,
            name = name,
            email = email
        )
    }
}

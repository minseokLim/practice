package com.minseoklim.toyproject2024.member.dto.ui

import com.minseoklim.toyproject2024.member.domain.model.Email
import com.minseoklim.toyproject2024.member.domain.model.Name
import com.minseoklim.toyproject2024.member.domain.model.Password
import com.minseoklim.toyproject2024.member.dto.application.UpdateMemberInput
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

data class UpdateMemberRequest(
    @get:Size(min = 1, message = Password.ERR_MSG)
    val password: String?,

    @get:Pattern(regexp = Name.REGEX_STR, message = Name.ERR_MSG)
    val name: String,

    @get:Pattern(regexp = Email.REGEX_STR, message = Email.ERR_MSG)
    val email: String?,

    val version: Long
) {
    fun toInput(): UpdateMemberInput {
        return UpdateMemberInput(
            password = password,
            name = name,
            email = email,
            version = version
        )
    }
}

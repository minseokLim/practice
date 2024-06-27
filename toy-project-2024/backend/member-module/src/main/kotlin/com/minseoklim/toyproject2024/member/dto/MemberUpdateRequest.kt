package com.minseoklim.toyproject2024.member.dto

import com.minseoklim.toyproject2024.member.domain.Email
import com.minseoklim.toyproject2024.member.domain.Member
import com.minseoklim.toyproject2024.member.domain.Name
import com.minseoklim.toyproject2024.member.domain.Password
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.springframework.security.crypto.password.PasswordEncoder

data class MemberUpdateRequest(
    @get:NotBlank(message = Password.ERR_MSG)
    val password: String,

    @get:Pattern(regexp = Name.REGEX_STR, message = Name.ERR_MSG)
    val name: String,

    @get:Pattern(regexp = Email.REGEX_STR, message = Email.ERR_MSG)
    val email: String
) {
    fun toEntity(original: Member, passwordEncoder: PasswordEncoder): Member {
        return Member(original.loginId?.value, passwordEncoder.encode(password), name, email)
    }
}

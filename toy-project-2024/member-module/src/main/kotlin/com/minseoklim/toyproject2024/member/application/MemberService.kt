package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.LoginIdValidator
import com.minseoklim.toyproject2024.member.domain.MemberRepository
import com.minseoklim.toyproject2024.member.dto.MemberJoinRequest
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val loginIdValidator: LoginIdValidator,
    private val passwordEncoder: PasswordEncoder,
) {
    fun join(request: MemberJoinRequest): MemberResponse {
        loginIdValidator.checkExistence(request.loginId)
        val member = memberRepository.save(request.toEntity(passwordEncoder))
        return MemberResponse.of(member)
    }
}

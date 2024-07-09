package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.domain.service.LoginIdValidator
import com.minseoklim.toyproject2024.member.dto.JoinMemberRequest
import com.minseoklim.toyproject2024.member.dto.JoinMemberResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class JoinMemberService(
    private val memberRepository: MemberRepository,
    private val loginIdValidator: LoginIdValidator
) {
    fun join(request: JoinMemberRequest): JoinMemberResponse {
        loginIdValidator.checkExistence(request.loginId)
        val member = memberRepository.save(request.toEntity())
        return JoinMemberResponse.of(member)
    }
}

package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.domain.service.LoginIdValidator
import com.minseoklim.toyproject2024.member.dto.application.JoinMemberInput
import com.minseoklim.toyproject2024.member.dto.application.JoinMemberOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class JoinMemberService(
    private val memberRepository: MemberRepository,
    private val loginIdValidator: LoginIdValidator
) {
    fun join(input: JoinMemberInput): JoinMemberOutput {
        loginIdValidator.checkExistence(input.loginId)
        val member = memberRepository.save(input.toEntity())
        return JoinMemberOutput.from(member)
    }
}

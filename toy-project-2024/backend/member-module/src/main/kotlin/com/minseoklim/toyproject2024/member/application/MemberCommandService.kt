package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.domain.service.LoginIdValidator
import com.minseoklim.toyproject2024.member.dto.MemberJoinRequest
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import com.minseoklim.toyproject2024.member.dto.MemberUpdateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberCommandService(
    private val memberRepository: MemberRepository,
    private val loginIdValidator: LoginIdValidator
) {
    fun join(request: MemberJoinRequest): MemberResponse {
        loginIdValidator.checkExistence(request.loginId)
        val member = memberRepository.save(request.toEntity())
        return MemberResponse.of(member)
    }

    fun update(id: Int, request: MemberUpdateRequest): MemberResponse {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.validateVersion(request.version)
        member.update(request.toEntity(member))
        return MemberResponse.of(member)
    }

    fun delete(id: Int) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.delete()
    }
}

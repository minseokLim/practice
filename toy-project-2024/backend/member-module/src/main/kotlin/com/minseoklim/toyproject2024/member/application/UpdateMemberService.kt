package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.dto.UpdateMemberRequest
import com.minseoklim.toyproject2024.member.dto.UpdateMemberResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateMemberService(
    private val memberRepository: MemberRepository
) {
    fun update(id: Int, request: UpdateMemberRequest): UpdateMemberResponse {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.validateVersion(request.version)
        member.update(request.toEntity(member))
        return UpdateMemberResponse.of(member)
    }
}

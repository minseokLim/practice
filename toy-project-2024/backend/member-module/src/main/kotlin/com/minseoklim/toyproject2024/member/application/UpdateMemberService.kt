package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.dto.application.UpdateMemberInput
import com.minseoklim.toyproject2024.member.dto.application.UpdateMemberOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UpdateMemberService(
    private val memberRepository: MemberRepository
) {
    fun update(
        id: Long,
        input: UpdateMemberInput
    ): UpdateMemberOutput {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.validateVersion(input.version)
        member.update(input.toEntity(member))
        return UpdateMemberOutput.from(member)
    }
}

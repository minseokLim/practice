package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.common.domain.type.ErrorCode
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository

object MemberServiceHelper {
    fun getMember(
        memberRepository: MemberRepository,
        id: Int
    ): Member {
        return memberRepository.findById(id)
            .orElseThrow { NotFoundException(ErrorCode.MEMBER_NOT_FOUND) }
    }
}

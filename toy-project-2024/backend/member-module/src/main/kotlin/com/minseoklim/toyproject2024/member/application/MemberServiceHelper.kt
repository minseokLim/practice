package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.member.domain.Member
import com.minseoklim.toyproject2024.member.domain.MemberRepository

object MemberServiceHelper {
    fun getMember(memberRepository: MemberRepository, id: Int): Member {
        return memberRepository.findById(id)
            .orElseThrow { NotFoundException("MEMBER_NOT_FOUND", "찾을 수 없는 회원입니다.") }
    }
}

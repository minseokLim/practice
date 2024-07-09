package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteMemberService(
    private val memberRepository: MemberRepository
) {
    fun delete(id: Int) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.delete()
    }
}

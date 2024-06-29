package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.MemberRepository
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryService(
    private val memberRepository: MemberRepository
) {
    fun list(pageable: Pageable): Page<MemberResponse> {
        val members = memberRepository.findAll(pageable)
        return members.map { MemberResponse.of(it) }
    }

    fun get(id: Int): MemberResponse {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        return MemberResponse.of(member)
    }
}

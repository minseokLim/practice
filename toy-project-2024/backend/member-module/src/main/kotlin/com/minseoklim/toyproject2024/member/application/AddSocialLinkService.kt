package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.dto.AddSocialLinkRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddSocialLinkService(
    private val memberRepository: MemberRepository
) {
    fun addSocialLink(id: Int, request: AddSocialLinkRequest) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.addSocialLink(request.socialType, request.socialId)
    }
}

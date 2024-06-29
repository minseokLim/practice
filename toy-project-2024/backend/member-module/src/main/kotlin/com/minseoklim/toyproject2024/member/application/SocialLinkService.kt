package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.MemberRepository
import com.minseoklim.toyproject2024.member.domain.SocialType
import com.minseoklim.toyproject2024.member.dto.SocialLinkCreateRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SocialLinkService(
    private val memberRepository: MemberRepository
) {
    fun addSocialLink(id: Int, request: SocialLinkCreateRequest) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.addSocialLink(request.socialType, request.socialId)
    }

    fun deleteSocialLink(id: Int, socialType: SocialType) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.deleteSocialLink(socialType)
    }
}

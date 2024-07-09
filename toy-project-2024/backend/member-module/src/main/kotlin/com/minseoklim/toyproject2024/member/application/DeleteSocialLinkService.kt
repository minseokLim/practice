package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class DeleteSocialLinkService(
    private val memberRepository: MemberRepository
) {
    fun deleteSocialLink(id: Int, socialType: SocialType) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.deleteSocialLink(socialType)
    }
}

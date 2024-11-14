package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.application.dto.AddSocialLinkInput
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class AddSocialLinkService(
    private val memberRepository: MemberRepository
) {
    fun addSocialLink(
        id: Long,
        input: AddSocialLinkInput
    ) {
        val member = MemberServiceHelper.getMember(memberRepository, id)
        member.addSocialLink(input.socialType, input.socialId)
    }
}

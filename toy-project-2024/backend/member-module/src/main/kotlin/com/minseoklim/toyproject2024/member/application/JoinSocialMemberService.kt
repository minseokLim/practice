package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import com.minseoklim.toyproject2024.member.dto.application.JoinSocialMemberOutput
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class JoinSocialMemberService(
    private val memberRepository: MemberRepository
) {
    fun join(socialType: SocialType, attributes: Map<String, Any>): JoinSocialMemberOutput {
        val member = memberRepository.save(socialType.toMemberEntity(attributes))
        return JoinSocialMemberOutput.of(member)
    }
}

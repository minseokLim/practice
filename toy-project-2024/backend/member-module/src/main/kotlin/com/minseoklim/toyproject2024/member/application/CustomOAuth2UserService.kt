package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.auth.domain.Role
import com.minseoklim.toyproject2024.member.domain.MemberRepository
import com.minseoklim.toyproject2024.member.domain.SocialType
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomOAuth2UserService(
    private val memberRepository: MemberRepository
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val defaultOAuth2UserService = DefaultOAuth2UserService()

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val attributes = defaultOAuth2UserService.loadUser(userRequest).attributes
        val socialType = SocialType.valueOf(userRequest.clientRegistration.registrationId.uppercase())
        val socialId = socialType.extractSocialId(attributes)

        val foundMember = memberRepository.findBySocialTypeAndSocialId(socialType, socialId)
        val member = foundMember ?: memberRepository.save(socialType.toMemberEntity(attributes))

        return object : OAuth2User {
            override fun getName(): String {
                return member.id.toString()
            }

            override fun getAttributes(): Map<String, Any> {
                return attributes
            }

            override fun getAuthorities(): Set<Role> {
                return member.getRoles()
            }
        }
    }
}

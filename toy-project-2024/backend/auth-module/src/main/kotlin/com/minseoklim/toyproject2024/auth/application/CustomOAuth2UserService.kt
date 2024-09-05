package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.member.application.JoinSocialMemberService
import com.minseoklim.toyproject2024.member.application.QueryMemberService
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class CustomOAuth2UserService(
    private val queryMemberService: QueryMemberService,
    private val joinSocialMemberService: JoinSocialMemberService
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private val defaultOAuth2UserService = DefaultOAuth2UserService()

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {
        val attributes = defaultOAuth2UserService.loadUser(userRequest).attributes
        val socialType = SocialType.valueOf(userRequest.clientRegistration.registrationId.uppercase())
        val socialId = socialType.extractSocialId(attributes)

        return queryMemberService.findBySocialTypeAndSocialId(socialType, socialId)?.let {
            CustomOAuth2User(it.id, attributes, it.roles)
        } ?: joinSocialMemberService.join(socialType, attributes).let {
            CustomOAuth2User(it.id, attributes, it.roles)
        }
    }

    private class CustomOAuth2User(
        private val id: Int,
        private val attributes: Map<String, Any>,
        private val roles: Set<Role>
    ) : OAuth2User {
        override fun getName(): String {
            return id.toString()
        }

        override fun getAttributes(): Map<String, Any> {
            return attributes
        }

        override fun getAuthorities(): Set<Role> {
            return roles
        }
    }
}

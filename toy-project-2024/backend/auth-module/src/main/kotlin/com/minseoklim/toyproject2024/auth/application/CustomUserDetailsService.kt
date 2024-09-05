package com.minseoklim.toyproject2024.auth.application

import com.minseoklim.toyproject2024.member.application.QueryMemberService
import com.minseoklim.toyproject2024.member.dto.application.QueryMemberOutput
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CustomUserDetailsService(
    private val queryMemberService: QueryMemberService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = queryMemberService.findByLoginId(username)
            ?: throw UsernameNotFoundException("회원을 찾을 수 없습니다.")
        return toUserDetails(member)
    }

    private fun toUserDetails(member: QueryMemberOutput): UserDetails {
        return User.builder()
            .username(member.id.toString())
            .password(member.password)
            .authorities(member.roles)
            .disabled(member.isDeleted)
            .build()
    }
}

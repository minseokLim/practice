package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.member.domain.Member
import com.minseoklim.toyproject2024.member.domain.MemberRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CustomUserDetailsService(
    private val memberRepository: MemberRepository
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val member = memberRepository.findByLoginIdValue(username)
            ?: throw UsernameNotFoundException("회원을 찾을 수 없습니다.")
        return toUserDetails(member)
    }

    private fun toUserDetails(member: Member): UserDetails {
        return User.builder()
            .username(member.id.toString())
            .password(member.password.value)
            .authorities(member.getRoles())
            .build()
    }
}

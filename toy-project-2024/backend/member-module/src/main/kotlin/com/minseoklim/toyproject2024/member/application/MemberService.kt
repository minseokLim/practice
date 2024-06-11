package com.minseoklim.toyproject2024.member.application

import com.minseoklim.toyproject2024.auth.application.LogoutService
import com.minseoklim.toyproject2024.common.exception.NotFoundException
import com.minseoklim.toyproject2024.member.domain.LoginIdValidator
import com.minseoklim.toyproject2024.member.domain.Member
import com.minseoklim.toyproject2024.member.domain.MemberRepository
import com.minseoklim.toyproject2024.member.dto.MemberJoinRequest
import com.minseoklim.toyproject2024.member.dto.MemberResponse
import com.minseoklim.toyproject2024.member.dto.MemberUpdateRequest
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository,
    private val loginIdValidator: LoginIdValidator,
    private val passwordEncoder: PasswordEncoder,
    private val logoutService: LogoutService
) {
    fun join(request: MemberJoinRequest): MemberResponse {
        loginIdValidator.checkExistence(request.loginId)
        val member = memberRepository.save(request.toEntity(passwordEncoder))
        return MemberResponse.of(member)
    }

    @Transactional(readOnly = true)
    fun list(pageable: Pageable): Page<MemberResponse> {
        val members = memberRepository.findAll(pageable)
        return members.map { MemberResponse.of(it) }
    }

    @Transactional(readOnly = true)
    fun get(id: Int): MemberResponse {
        val member = getMember(id)
        return MemberResponse.of(member)
    }

    fun update(id: Int, request: MemberUpdateRequest): MemberResponse {
        val member = getMember(id)
        member.update(request.toEntity(member, passwordEncoder))
        return MemberResponse.of(member)
    }

    fun delete(id: Int) {
        val member = getMember(id)
        member.delete()
        logoutService.logoutAll(id)
    }

    private fun getMember(id: Int): Member {
        return memberRepository.findById(id)
            .orElseThrow { NotFoundException("MEMBER_NOT_FOUND", "찾을 수 없는 회원입니다.") }
    }
}

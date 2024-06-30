package com.minseoklim.toyproject2024.member.domain.service

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.springframework.stereotype.Component

@Component
class LoginIdValidator(
    private val memberRepository: MemberRepository
) {
    fun checkExistence(loginId: String) {
        val foundCount = memberRepository.countByLoginIdValue(loginId)
        if (foundCount > 0) {
            throw BadRequestException("OCCUPIED_LOGIN_ID", "이미 사용 중인 로그인 ID입니다.")
        }
    }
}

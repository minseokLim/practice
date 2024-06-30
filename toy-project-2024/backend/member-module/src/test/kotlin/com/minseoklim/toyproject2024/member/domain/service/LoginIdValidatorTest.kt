package com.minseoklim.toyproject2024.member.domain.service

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class LoginIdValidatorTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository
    private lateinit var loginIdValidator: LoginIdValidator

    @BeforeEach
    fun setUp() {
        loginIdValidator = LoginIdValidator(memberRepository)
    }

    @Test
    fun checkExistence() {
        // given
        val loginId = "member"

        // when, then
        assertThatNoException().isThrownBy {
            loginIdValidator.checkExistence(loginId)
        }

        // given
        memberRepository.save(Member(loginId, "password", "name", "test@test.com"))

        // when, then
        assertThatThrownBy {
            loginIdValidator.checkExistence(loginId)
        }.isInstanceOf(BadRequestException::class.java)
    }
}

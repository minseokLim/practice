package com.minseoklim.toyproject2024.member.domain.service

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
@Import(LoginIdValidator::class)
class LoginIdValidatorTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @Autowired
    private lateinit var loginIdValidator: LoginIdValidator

    @BeforeEach
    fun setUp() {
        memberRepository.deleteAll()

        PasswordEncodeUtil.init(BCryptPasswordEncoder())
        TextEncryptUtil.init(Encryptors.noOpText())
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

package com.minseoklim.toyproject2024.member.util

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.member.domain.model.Member
import com.minseoklim.toyproject2024.member.domain.model.Role
import com.minseoklim.toyproject2024.member.domain.model.SocialType
import com.minseoklim.toyproject2024.member.domain.repository.MemberRepository
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.context.ActiveProfiles

@DataJpaTest
@ActiveProfiles("test")
class MemberFilterParserTest {

    @Autowired
    private lateinit var memberRepository: MemberRepository

    @BeforeEach
    fun setUp() {
        memberRepository.deleteAll()

        PasswordEncodeUtil.init(BCryptPasswordEncoder())
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun parse() {
        // given
        val member = memberRepository.save(
            Member(
                loginId = LOGIN_ID,
                password = PASSWORD,
                name = NAME,
                email = EMAIL
            )
        )
        member.addRole(Role.ADMIN)
        member.addSocialLink(SocialType.GOOGLE, "1234")
        memberRepository.save(
            Member(
                loginId = "different",
                password = PASSWORD,
                name = "different",
                email = "different@test.com"
            )
        ).delete()

        // given
        val filter1 = "[loginId:$LOGIN_ID]"

        // when
        val predicate1 = MemberFilterParser.parse(filter1)

        // then
        assertThat(memberRepository.findAll(predicate1)).containsExactly(member)

        // given
        val filter2 = "[name:$NAME]"

        // when
        val predicate2 = MemberFilterParser.parse(filter2)

        // then
        assertThat(memberRepository.findAll(predicate2)).containsExactly(member)

        // given
        val filter3 = "[email:$EMAIL]"

        // when
        val predicate3 = MemberFilterParser.parse(filter3)

        // then
        assertThat(memberRepository.findAll(predicate3)).containsExactly(member)

        // given
        val filter4 = "[role:ADMIN]"

        // when
        val predicate4 = MemberFilterParser.parse(filter4)

        // then
        assertThat(memberRepository.findAll(predicate4)).containsExactly(member)

        // given
        val filter5 = "[socialType:GOOGLE]"

        // when
        val predicate5 = MemberFilterParser.parse(filter5)

        // then
        assertThat(memberRepository.findAll(predicate5)).containsExactly(member)

        // given
        val filter6 = "[isDeleted:false]"

        // when
        val predicate6 = MemberFilterParser.parse(filter6)

        // then
        assertThat(memberRepository.findAll(predicate6)).containsExactly(member)

        // given
        val filter7 = "[loginId:$LOGIN_ID,name:$NAME,email:$EMAIL,role:ADMIN,socialType:GOOGLE,isDeleted:false]"

        // when
        val predicate7 = MemberFilterParser.parse(filter7)

        // then
        assertThat(memberRepository.findAll(predicate7)).containsExactly(member)

        // given
        val filter8 = "loginId:$LOGIN_ID"

        // when, then
        assertThatThrownBy {
            MemberFilterParser.parse(filter8)
        }.isInstanceOf(BadRequestException::class.java)

        // given
        val filter9 = "[socialId:1234]"

        // when, then
        assertThatThrownBy {
            MemberFilterParser.parse(filter9)
        }.isInstanceOf(BadRequestException::class.java)
    }

    companion object {
        private const val LOGIN_ID = "test1234"
        private const val PASSWORD = "password"
        private const val NAME = "testName"
        private const val EMAIL = "test@test.com"
    }
}

package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.exception.BadRequestException
import com.minseoklim.toyproject2024.common.util.PasswordEncodeUtil
import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.encrypt.Encryptors
import org.springframework.test.util.ReflectionTestUtils

class MemberTest {
    @BeforeEach
    fun setUp() {
        PasswordEncodeUtil.init(BCryptPasswordEncoder())
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun addRole() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )

        // when
        member.addRole(Role.ADMIN)

        // then
        assertThat(member.getRoles()).contains(Role.ADMIN)

        // when, then
        assertThatThrownBy {
            member.addRole(Role.ADMIN)
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun deleteRole() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        member.addRole(Role.ADMIN)

        // when
        member.deleteRole(Role.ADMIN)

        // then
        assertThat(member.getRoles()).doesNotContain(Role.ADMIN)

        // when, then
        assertThatThrownBy {
            member.deleteRole(Role.MEMBER)
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun getRoles() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )

        // when
        val roles = member.getRoles()

        // then
        assertThat(roles).hasSize(1)
    }

    @Test
    fun addSocialLink() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )

        // when
        member.addSocialLink(SocialType.GOOGLE, "1234")

        // then
        assertThat(member.getSocialLinks()).contains(SocialLink(SocialType.GOOGLE, "1234"))

        // when, then
        assertThatThrownBy {
            member.addSocialLink(SocialType.GOOGLE, "5678")
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun deleteSocialLink() {
        // given
        val member1 = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        member1.addSocialLink(SocialType.GOOGLE, "1234")

        // when
        member1.deleteSocialLink(SocialType.GOOGLE)

        // then
        assertThat(member1.getSocialLinks()).isEmpty()

        // given
        val member2 = Member(
            name = "testName",
            email = "test@test.com",
            socialType = SocialType.GOOGLE,
            socialId = "1234"
        )

        // when, then
        assertThatThrownBy {
            member2.deleteSocialLink(SocialType.GOOGLE)
        }.isInstanceOf(BadRequestException::class.java)

        // given
        val member3 = Member(
            name = "testName",
            email = "test@test.com",
            socialType = SocialType.GOOGLE,
            socialId = "1234"
        )
        member3.addSocialLink(SocialType.KAKAO, "1234")

        // when, then
        assertThatNoException().isThrownBy {
            member3.deleteSocialLink(SocialType.KAKAO)
        }
    }

    @Test
    fun getSocialLinks() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )

        // when
        val socialLinks = member.getSocialLinks()

        // then
        assertThat(socialLinks).isEmpty()
    }

    @Test
    fun update() {
        // given
        val memberWithLoginId = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        val otherWithLoginId = Member(
            loginId = "test1234",
            password = "newPassword",
            name = "newName",
            email = "new@test.com"
        )

        // when
        memberWithLoginId.update(otherWithLoginId)

        // then
        assertThat(memberWithLoginId.password).isEqualTo(otherWithLoginId.password)
        assertThat(memberWithLoginId.name).isEqualTo(otherWithLoginId.name)
        assertThat(memberWithLoginId.email).isEqualTo(otherWithLoginId.email)

        // given
        val memberWithoutLoginId = Member(
            name = "testName",
            email = "test@test.com",
            socialType = SocialType.GOOGLE,
            socialId = "1234"
        )
        val otherWithoutLoginId = Member(
            name = "newName",
            email = "new@test.com",
            socialType = SocialType.GOOGLE,
            socialId = "1234"
        )

        // when
        memberWithoutLoginId.update(otherWithoutLoginId)

        // then
        assertThat(memberWithoutLoginId.name).isEqualTo(otherWithoutLoginId.name)
        assertThat(memberWithoutLoginId.email).isEqualTo(otherWithoutLoginId.email)

        // when, then
        assertThatThrownBy {
            memberWithLoginId.update(otherWithoutLoginId)
        }.isInstanceOf(BadRequestException::class.java)

        // when, then
        assertThatThrownBy {
            otherWithoutLoginId.update(memberWithLoginId)
        }.isInstanceOf(BadRequestException::class.java)
    }

    @Test
    fun delete() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        ReflectionTestUtils.setField(member, "id", 1)

        // when
        member.delete()

        // then
        assertThat(member.isDeleted).isTrue
    }

    @Test
    fun validateVersion() {
        // given
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        val memberVersion = 1L
        ReflectionTestUtils.setField(member, "version", memberVersion)

        // when, then
        assertThatNoException().isThrownBy {
            member.validateVersion(memberVersion)
        }

        // when, then
        assertThatThrownBy {
            member.validateVersion(2L)
        }.isInstanceOf(OptimisticLockingFailureException::class.java)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val member1 = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        val member2 = Member(
            loginId = "other1234",
            password = "otherPassword",
            name = "otherName",
            email = "other@other.com"
        )
        val member3 = Member(
            loginId = "other1234",
            password = "otherPassword",
            name = "otherName",
            email = "other@other.com"
        )
        ReflectionTestUtils.setField(member1, "id", 1)
        ReflectionTestUtils.setField(member2, "id", 1)

        // when, then
        assertThat(setOf(member1, member2, member3)).hasSize(2)
    }
}

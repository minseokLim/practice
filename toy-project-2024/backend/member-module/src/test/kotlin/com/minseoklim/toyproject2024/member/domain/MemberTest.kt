package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import com.minseoklim.toyproject2024.common.exception.BadRequestException
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.test.util.ReflectionTestUtils

class MemberTest {

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
        val member = Member(
            loginId = "test1234",
            password = "password",
            name = "testName",
            email = "test@test.com"
        )
        val other = Member(
            loginId = "test1234",
            password = "newPassword",
            name = "newName",
            email = "new@test.com"
        )

        // when
        member.update(other)

        // then
        assertThat(member.password).isEqualTo(other.password)
        assertThat(member.name).isEqualTo(other.name)
        assertThat(member.email).isEqualTo(other.email)
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

        // when
        member.delete()

        // then
        assertThat(member.isDeleted).isTrue
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
        ReflectionTestUtils.setField(member1, "id", 1)
        ReflectionTestUtils.setField(member2, "id", 1)
        val set = hashSetOf<Member>()

        // when
        set.add(member1)
        set.add(member2)

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

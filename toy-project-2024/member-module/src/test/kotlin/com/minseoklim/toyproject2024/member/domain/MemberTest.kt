package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        assertThat(member.memberRoles.values.map { it.role }).contains(Role.ADMIN)
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
}

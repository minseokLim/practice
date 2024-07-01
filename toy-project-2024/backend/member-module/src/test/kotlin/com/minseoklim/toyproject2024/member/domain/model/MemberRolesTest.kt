package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemberRolesTest {

    @Test
    fun addRole() {
        // given
        val memberRoles = MemberRoles()

        // when
        memberRoles.addRole(Role.MEMBER)

        // then
        assertThat(memberRoles.getRoles()).contains(Role.MEMBER)
    }

    @Test
    fun getRoles() {
        // given
        val memberRoles = MemberRoles()
        memberRoles.addRole(Role.MEMBER)
        memberRoles.addRole(Role.ADMIN)

        // when
        val roles = memberRoles.getRoles()

        // then
        assertThat(roles).contains(Role.MEMBER, Role.ADMIN)
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val memberRoles1 = MemberRoles().apply { addRole(Role.MEMBER) }
        val memberRoles2 = MemberRoles().apply { addRole(Role.MEMBER) }
        val memberRoles3 = MemberRoles().apply { addRole(Role.ADMIN) }

        // when, then
        TestUtil.testEqualsAndHashCode(memberRoles1, memberRoles2, memberRoles3)
    }
}

package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
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
        val set = hashSetOf<MemberRoles>()

        // when
        set.add(MemberRoles().apply { addRole(Role.MEMBER) })
        set.add(MemberRoles().apply { addRole(Role.MEMBER) })

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

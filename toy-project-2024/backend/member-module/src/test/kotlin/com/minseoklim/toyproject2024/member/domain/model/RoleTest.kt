package com.minseoklim.toyproject2024.member.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RoleTest {
    @Test
    fun getAuthority() {
        // given
        val role = Role.ADMIN

        // when
        val authority = role.authority

        // then
        assertThat(authority).isEqualTo(Role.ROLE_PREFIX + role.name)
    }

    @Test
    fun from() {
        // given
        val role = Role.ADMIN

        // when
        val result = Role.from(role.authority)

        // then
        assertThat(result).isEqualTo(role)
    }
}

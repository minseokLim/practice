package com.minseoklim.toyproject2024.member.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemberRoleTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val memberRole1 = MemberRole(Role.MEMBER)
        val memberRole2 = MemberRole(Role.MEMBER)
        val memberRole3 = MemberRole(Role.ADMIN)

        // when, then
        assertThat(setOf(memberRole1, memberRole2, memberRole3)).hasSize(2)
    }
}

package com.minseoklim.toyproject2024.member.domain

import com.minseoklim.toyproject2024.auth.domain.Role
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MemberRoleTest {

    @Test
    fun equalsAndHashCode() {
        // given
        val set = hashSetOf<MemberRole>()

        // when
        set.add(MemberRole(Role.MEMBER))
        set.add(MemberRole(Role.MEMBER))

        // then
        assertThat(set.size).isEqualTo(1)
    }
}

package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.junit.jupiter.api.Test

class MemberRoleTest {
    @Test
    fun equalsAndHashCode() {
        // given
        val memberRole1 = MemberRole(Role.MEMBER)
        val memberRole2 = MemberRole(Role.MEMBER)
        val memberRole3 = MemberRole(Role.ADMIN)

        // when, then
        TestUtil.testEqualsAndHashCode(memberRole1, memberRole2, memberRole3)
    }
}

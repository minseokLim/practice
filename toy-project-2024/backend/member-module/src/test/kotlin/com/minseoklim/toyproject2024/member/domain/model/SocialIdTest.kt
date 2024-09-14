package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class SocialIdTest {
    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            SocialId("test1234")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            SocialId("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val socialId1 = SocialId("test1234")
        val socialId2 = SocialId("test1234")
        val socialId3 = SocialId("other1234")

        // when, then
        TestUtil.testEqualsAndHashCode(socialId1, socialId2, socialId3)
    }
}

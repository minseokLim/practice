package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class EmailTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Email("test@test.com")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Email("test")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val email1 = Email("test@test.com")
        val email2 = Email("test@test.com")
        val email3 = Email("other@other.com")

        // when, then
        TestUtil.testEqualsAndHashCode(email1, email2, email3)
    }
}

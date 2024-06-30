package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.Test

class PasswordTest {

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Password("test1234")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Password("")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val password1 = Password("test1234")
        val password2 = Password("test1234")
        val password3 = Password("other1234")

        // when, then
        TestUtil.testEqualsAndHashCode(password1, password2, password3)
    }
}

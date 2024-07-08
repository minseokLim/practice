package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class EmailTest {

    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

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

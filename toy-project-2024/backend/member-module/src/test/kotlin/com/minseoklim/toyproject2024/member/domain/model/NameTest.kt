package com.minseoklim.toyproject2024.member.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import com.minseoklim.toyproject2024.test.util.TestUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class NameTest {

    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Name("임민석")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Name("임민석@")
        }
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val name1 = Name("임민석")
        val name2 = Name("임민석")
        val name3 = Name("other")

        // when, then
        TestUtil.testEqualsAndHashCode(name1, name2, name3)
    }
}

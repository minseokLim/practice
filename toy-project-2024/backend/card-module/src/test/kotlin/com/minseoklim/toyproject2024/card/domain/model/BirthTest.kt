package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class BirthTest {
    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Birth("940101")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Birth("19940101")
        }
    }
}

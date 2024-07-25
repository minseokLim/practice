package com.minseoklim.toyproject2024.card.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class Pwd2digitTest {

    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Pwd2digit("12")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Pwd2digit("1234")
        }
    }
}

package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class ReceiverTest {
    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Receiver("홍길동", "010-1234-5678")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Receiver("", "010-1234-5678")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Receiver("홍길동", "01012345678")
        }
    }
}

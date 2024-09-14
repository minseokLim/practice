package com.minseoklim.toyproject2024.order.domain.model

import com.minseoklim.toyproject2024.common.util.TextEncryptUtil
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.assertj.core.api.Assertions.assertThatNoException
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.encrypt.Encryptors

class AddressTest {
    @BeforeEach
    fun setUp() {
        TextEncryptUtil.init(Encryptors.noOpText())
    }

    @Test
    fun constructor() {
        // when, then
        assertThatNoException().isThrownBy {
            Address("서울시 강남구 역삼동", "테헤란로 427", "06164")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Address("", "테헤란로 427", "06164")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Address("서울시 강남구 역삼동", "", "06164")
        }

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Address("서울시 강남구 역삼동", "테헤란로 427", "0616")
        }
    }
}

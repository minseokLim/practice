package com.minseoklim.toyproject2024.common.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConsistentHashUtilTest {
    @Test
    fun hash() {
        // given
        val input = "test"
        val hashed1 = ConsistentHashUtil.hash(input)

        // when
        val hashed2 = ConsistentHashUtil.hash(input)

        // then
        assertThat(hashed2).isEqualTo(hashed1)
    }
}

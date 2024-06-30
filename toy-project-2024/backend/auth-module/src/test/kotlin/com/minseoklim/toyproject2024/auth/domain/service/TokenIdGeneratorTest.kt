package com.minseoklim.toyproject2024.auth.domain.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TokenIdGeneratorTest {

    @Test
    fun generate() {
        // when
        val tokenId = TokenIdGenerator.generate()

        // then
        assertThat(tokenId).isNotBlank
    }
}

package com.minseoklim.toyproject2024.auth.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TokenTest {
    @Test
    fun delete() {
        // given
        val token = Token(
            id = "id",
            memberId = 1L,
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )

        // when
        token.delete()

        // then
        assertThat(token.isDeleted).isTrue
    }

    @Test
    fun equalsAndHashCode() {
        // given
        val token1 = Token(
            id = "id",
            memberId = 1L,
            accessToken = "accessToken",
            refreshToken = "refreshToken"
        )
        val token2 = Token(
            id = "id",
            memberId = 2,
            accessToken = "other accessToken",
            refreshToken = "other refreshToken"
        )
        val token3 = Token(
            id = "other id",
            memberId = 3,
            accessToken = "other accessToken",
            refreshToken = "other refreshToken"
        )

        // when, then
        assertThat(setOf(token1, token2, token3)).hasSize(2)
    }
}

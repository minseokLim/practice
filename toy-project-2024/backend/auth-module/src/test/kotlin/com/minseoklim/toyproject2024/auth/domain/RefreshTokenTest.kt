package com.minseoklim.toyproject2024.auth.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class RefreshTokenTest {

    @Test
    fun delete() {
        // given
        val refreshToken = RefreshToken(
            id = "id",
            memberId = 1,
            content = "content"
        )

        // when
        refreshToken.delete()

        // then
        assertThat(refreshToken.isDeleted).isTrue
    }
}

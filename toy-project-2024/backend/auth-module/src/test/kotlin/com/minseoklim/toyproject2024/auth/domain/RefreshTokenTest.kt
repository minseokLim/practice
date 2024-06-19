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

    @Test
    fun equalsAndHashCode() {
        // given
        val refreshToken1 = RefreshToken(
            id = "id",
            memberId = 1,
            content = "content"
        )
        val refreshToken2 = RefreshToken(
            id = "id",
            memberId = 2,
            content = "other content"
        )
        val set = hashSetOf<RefreshToken>()

        // when
        set.add(refreshToken1)
        set.add(refreshToken2)

        // then
        assertThat(set.size).isEqualTo(1)
    }
}
